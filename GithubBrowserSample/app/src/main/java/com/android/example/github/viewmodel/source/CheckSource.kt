package com.android.example.github.viewmodel.source

import com.android.example.github.AppExecutors
import com.android.example.github.api.GithubService
import com.android.example.github.repository.MyNetworkBoundResource
import com.android.example.github.testing.OpenForTesting
import com.android.example.github.vo.RemoteCall
import com.android.example.github.vo.UpdateCallBean
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author oldwang
 * @date 2019/1/16
 * Description:
 */
@OpenForTesting
@Singleton
class CheckSource @Inject constructor(
        private val appExecutors: AppExecutors,
        private val githubService: GithubService) {

    fun checkVersion(version: Int, callback: BaseDataSource.CallBack<UpdateCallBean>) {
        object : MyNetworkBoundResource<UpdateCallBean>(appExecutors) {
            override fun createCall(): RemoteCall<UpdateCallBean> {
                return githubService.checkVersion(version)
            }

        }.fetch(callback)

    }

}