package com.android.example.github.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.android.example.github.test.User
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author oldwang
 * @date 2019/1/16
 * Description:
 */
@RunWith(JUnit4::class)
class TestKotlin {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private var start = MutableLiveData<String>()
    val end = 5
    val empty = emptyArray<Int>()
    val fixedSizeArray = arrayOfNulls<Int>(10)
    private var arr = arrayOf(1, 2, 3, 4, 5)

    @Test
    fun test() {
        start.value = "赋值了"
        println(start.value)

        start.value = "再次赋值"
        println(start.value)

        println("-------Array Start--------")
        // 遍历
        haha@ for (value in arr) {
            if (value > 2) {
                continue@haha
            }
            print("value:$value ")
        }
        print(" || ")

        // 根据下标来遍历
        hehe@ for (index in arr.indices) {
            if (index == 0) {
                continue@hehe
            }
            print("index:$index ")
        }
        println()
        println("-------Array End----------")

        val result = 100
        when (result) {
            100 -> println("sss")
            99 -> println("ss")
            in 95..99 -> println("s")
            in 90..94 -> println("a")
            in 80..89 -> println("A")
            in 70..79 -> println("B")
            in 60..69 -> println("C")
            else -> println("failed")
        }

        when (result) {
            is Int -> println("Int")
            else -> println("非Int")
        }

        when (result) {
            doSomeThing(result) -> {
                println("doSomeThing 后")
            }
            else -> {
                print("非 doSomeThing : ")
                println(result)
            }
        }

    }

    private fun doSomeThing(x: Int) = x

    @Test
    fun testString() {
        val name: String? = "12138"
        val parseInt = parseInt(name!!)
        println(parseInt)

        var user = User("OldWang", 25)
        println("name: ${user.name}")
        println("age: ${user.age}")

        user = User("树上的鱼", 22)
        println("name: ${user.name}")
        println("age: ${user.age}")

    }

    private fun parseInt(str: String) = str.toInt()


}