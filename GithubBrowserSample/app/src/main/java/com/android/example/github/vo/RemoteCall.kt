package com.android.example.github.vo

import com.android.example.github.viewmodel.source.BaseDataSource

/**
 * @author Sarah
 */
interface RemoteCall<T> {
    fun cancel()

    fun enqueue(callback: BaseDataSource.CallBack<T>)

    fun clone(): RemoteCall<T>
}