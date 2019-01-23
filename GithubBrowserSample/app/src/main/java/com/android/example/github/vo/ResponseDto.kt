package com.android.example.github.vo

import android.text.TextUtils

import com.android.example.github.util.GsonFactory

import org.json.JSONObject

@Suppress("NAME_SHADOWING")
/**
 * @date 2017/10/16.
 */
class ResponseDto(var code: Int?, var message: String?) {

    override fun toString(): String {
        return "ResponseDto{" +
                "message='" + message +
                "', errorCode=" + code + '}'.toString()
    }

    fun toShowMessage(): String {
        return "message='" + message + '\''.toString() +
                ", errorCode=" + code
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as ResponseDto?

        if (if (message != null) message != that!!.message else that!!.message != null) return false
        return if (code != null) code == that.code else that.code == null

    }

    override fun hashCode(): Int {
        var result = if (message != null) message!!.hashCode() else 0
        result = 31 * result + if (code != null) code!!.hashCode() else 0
        return result
    }

    companion object {

        fun convert(errorString: String?): ResponseDto {
            var msg = errorString
            var responseDto: ResponseDto
            try {
                if (msg!!.startsWith("\ufeff")) {
                    msg = msg.substring(1)
                }
                val errorObject = JSONObject(msg)
                val detail = errorObject.getString("detail")
                responseDto = if (!TextUtils.isEmpty(detail)) {
                    GsonFactory.create().fromJson(detail, ResponseDto::class.java)
                } else {
                    val message = errorObject.getString("message")
                    if (message != null) {
                        ResponseDto(-1, message)
                    } else {
                        ResponseDto(-1, msg)
                    }
                }
            } catch (e: Exception) {
                responseDto = if (msg != null) {
                    ResponseDto(-1, msg)
                } else {
                    ResponseDto(-1, "Unknown error")
                }
            }

            return responseDto
        }
    }
}
