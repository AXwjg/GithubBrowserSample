package com.android.example.github.test

/**
 * @author oldwang
 * @date 2019/1/16
 * Description:
 */
data class User(var name: String, var age: Int) {


    inner class State(val sample: Int) {
        fun getUser(): User {
            return this@User
        }

        fun getState(): State {
            return this@State
        }
    }
}