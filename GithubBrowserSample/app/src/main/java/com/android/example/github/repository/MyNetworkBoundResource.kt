package com.android.example.github.repository

import com.android.example.github.AppExecutors
import com.android.example.github.viewmodel.source.BaseDataSource
import com.android.example.github.vo.RemoteCall

import androidx.annotation.MainThread

/**
 * @author API
 */
abstract class MyNetworkBoundResource<T>(private val appExecutors: AppExecutors) {

    private fun fetchFromNetwork(callBack: BaseDataSource.CallBack<T>) {
        val apiResponse = createCall()
        appExecutors.mainThread().execute { apiResponse.enqueue(callBack) }
    }

    fun fetch(callBack: BaseDataSource.CallBack<T>) {
        fetchFromNetwork(callBack)
    }

    @MainThread
    protected abstract fun createCall(): RemoteCall<T>
}
