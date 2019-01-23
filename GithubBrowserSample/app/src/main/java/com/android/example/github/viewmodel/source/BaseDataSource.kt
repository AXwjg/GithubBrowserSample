package com.android.example.github.viewmodel.source

import com.android.example.github.vo.ResponseDto

/**
 * @author sarah
 * @date 2017/10/16.
 */

interface BaseDataSource<T> {

    interface CallBack<T> {
        /**
         * 成功回调
         */
        fun onLoaded(data: T)

        /**
         * 错误回调
         */
        fun onFailed(responseDto: ResponseDto)

        /**
         * 异常回调
         */
        fun onCatchException(ex: Exception)
    }
}
