package com.android.example.github.vo

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.Executor

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

/**
 * @author Sarah
 */
class RemoteCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type,
                     annotations: Array<Annotation>,
                     retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != RemoteCall::class.java) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                    "MyCall must have generic type (e.g., MyCall<ResponseBody>)")
        }
        val responseType = CallAdapter.Factory.getParameterUpperBound(0, returnType)
        val callbackExecutor = retrofit.callbackExecutor()
        return ErrorHandlingCallAdapter<Any>(responseType, callbackExecutor!!)
    }

    private class ErrorHandlingCallAdapter<R> internal constructor(private val responseType: Type, private val callbackExecutor: Executor) : CallAdapter<R, RemoteCall<R>> {

        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<R>): RemoteCall<R> {
            return RemoteCallAdapter(call, callbackExecutor)
        }
    }
}