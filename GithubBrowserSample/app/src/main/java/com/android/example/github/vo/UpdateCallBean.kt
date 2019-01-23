package com.android.example.github.vo

/**
 * @author oldwang
 * @date 2019/1/14
 * Description:
 */
class UpdateCallBean {

    var version: Int = 0
    var update: Boolean = false
    var downloadPath: String? = null
    var updateInfo: String? = null
        get() = if (field == null) "" else field

}
