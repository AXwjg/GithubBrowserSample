package com.android.example.github.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.example.github.AppExecutors
import com.android.example.github.repository.CheckRepository
import com.android.example.github.viewmodel.source.BaseDataSource
import com.android.example.github.vo.ResponseDto
import com.android.example.github.vo.UpdateCallBean
import javax.inject.Inject

/**
 * @author OldWang
 * @date 2019/1/16
 * Description:
 */
class CheckVersionViewModel @Inject constructor(
        application: Application,
        private val checkRepository: CheckRepository,
        private val appExecutors: AppExecutors
) : AndroidViewModel(application) {

    private val updateCallBean = MutableLiveData<UpdateCallBean>()

    private val TAG = "TAG ---"

    fun checkVersion(version: Int) {
        appExecutors.networkIO().execute {
            checkRepository.checkVersion(version, object : BaseDataSource.CallBack<UpdateCallBean> {
                override fun onLoaded(data: UpdateCallBean) {
                    Log.e(TAG, data.downloadPath)
                    Log.e(TAG, data.updateInfo)
                    Log.e(TAG, data.update.toString())
                    Log.e(TAG, "version: ${data.version}")
                    updateCallBean.postValue(data)
                }

                override fun onFailed(responseDto: ResponseDto) {
                    Log.e(TAG, responseDto.message)

                }

                override fun onCatchException(ex: Exception) {
                    Log.e(TAG, ex.message)
                }

            })
        }
    }


    public fun getUpdateCallBean(): MutableLiveData<UpdateCallBean> {
        return updateCallBean
    }
}