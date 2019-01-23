package com.android.example.github.vo


import android.util.Log

import com.android.example.github.viewmodel.source.BaseDataSource

import java.io.IOException
import java.util.concurrent.Executor

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Sarah
 * 远程呼叫适配器
 */
class RemoteCallAdapter<T> internal constructor(private val call: Call<T>, private val callbackExecutor: Executor) : RemoteCall<T> {

    override fun cancel() {
        call.cancel()
    }

    override fun enqueue(callback: BaseDataSource.CallBack<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onLoaded(response.body()!!)
                } else {
                    try {
                        if (call.isCanceled) {
                            Log.i(TAG, "Request was cancelled")
                        } else {
                            val responseDto = ResponseDto.convert(response.errorBody()!!.string())
                            callback.onFailed(responseDto)
                        }
                    } catch (e: IOException) {
                        onFailure(call, e)
                    }

                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (call.isCanceled) {
                    Log.i(TAG, "Request was cancelled")
                } else {
                    callback.onCatchException(Exception(t))
                }
            }
        })
    }

    override fun clone(): RemoteCall<T> {
        return RemoteCallAdapter(call.clone(), callbackExecutor)
    }

    companion object {

        private val TAG = "TAG"
    }
}