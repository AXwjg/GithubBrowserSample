package com.android.example.github.repository

import com.android.example.github.viewmodel.source.BaseDataSource
import com.android.example.github.viewmodel.source.CheckSource
import com.android.example.github.vo.UpdateCallBean
import javax.inject.Inject

/**
 * @author oldwang
 * @date 2019/1/16
 * Description:
 */
class CheckRepository @Inject constructor(
        private val checkSource: CheckSource) {

    fun checkVersion(version: Int, callback: BaseDataSource.CallBack<UpdateCallBean>) {
        checkSource.checkVersion(version, callback)
    }
}