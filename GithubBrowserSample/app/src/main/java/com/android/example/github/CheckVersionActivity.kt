package com.android.example.github

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.example.github.test.clazz.ObjectTest
import com.android.example.github.test.clazz.Rectangle
import com.android.example.github.test.clazz.Triangle
import com.android.example.github.viewmodel.CheckVersionViewModel
import com.android.example.github.vo.UpdateCallBean
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class CheckVersionActivity :
        AppCompatActivity(),
        HasSupportFragmentInjector,
        View.OnClickListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var checkViewModel: CheckVersionViewModel

    /** 检查更新的回调数据 */
    private lateinit var updateCallBean: UpdateCallBean

    val allowedValues = listOf("a", "b", "c")

    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_activity)

        val checkTv: Button = findViewById(R.id.check_tv)
        val printFun: Button = findViewById(R.id.fun_tv)
        val creatingTv: Button = findViewById(R.id.creating_tv)
        val objectBtn: Button = findViewById(R.id.object_tv)
        mProgressBar = findViewById(R.id.progress_bar)
        mProgressBar.visibility = View.GONE

        checkTv.setOnClickListener(this)
        printFun.setOnClickListener(this)
        creatingTv.setOnClickListener(this)
        objectBtn.setOnClickListener(this)

        initEvent()
        testMax()

    }

    private fun testMax() {
        val a = 10
        val b = 20
        var max = if (a > b) a else b
        when (a) {
            1 -> println(1)
            in 8..10 -> println(a)
            in 11..20 -> println(b)
            else -> println(b)
        }

        listOf(0, 1, 2, 3, 4).forEach lit@{
            if (it == 3) return@lit
            print("test lit@ $it | ")
        }
        println()

        listOf(0, 1, 2, 3, 4).forEach {
            if (it == 3) return@forEach
            print("test @forEach $it | ")
        }
        println()

    }

    private fun initEvent() {
        checkViewModel.getUpdateCallBean().observe(this, updateCallBeanObserve)
    }

    override fun onClick(v: View?) = when (v!!.id) {
        R.id.check_tv -> {
            checkViewModel.checkVersion(1)
            mProgressBar.visibility = View.VISIBLE
        }
        R.id.fun_tv -> {
            printLength("OldWang")
            printLength(12138)
            printLength(listOf(Any()))
        }
        R.id.creating_tv -> {
            val rectangle = Rectangle(5.0, 7.0)
            val triangle = Triangle(3.0, 6.0, 8.0)
            println("Area of rectangle is ${rectangle.calculateArea()}, its perimeter is ${rectangle.perimeter}")
            println("Area of triangle is ${triangle.calculateArea()}, its perimeter is ${triangle.perimeter}")
        }
        R.id.object_tv -> {
            val object1 = ObjectTest
            val object2 = ObjectTest

            object1.name = "OldWang"
            println("object1 Name: ${object1.name} | object2 Name: ${object2.name}")

            object2.name = "OldWang2"
            println("object1 Name: ${object1.name} | object2 Name: ${object2.name}")
        }
        else -> Toast.makeText(this, "点到什么神奇的东西了", Toast.LENGTH_SHORT).show()
    }

    private fun getStringLength(str: Any): Int? {
        if (str is String) {
            return str.length
        }
        return null
    }

    private fun testCall(str: String) =
            str.length

    private fun printLength(str: Any) {
        println("$str string length is ${getStringLength(str) ?: "... error,not a String"} ")
    }

    /** 是否需要更新数据监听 */
    private val updateCallBeanObserve = Observer<UpdateCallBean> {
        mProgressBar.visibility = View.GONE
        updateCallBean = it
        Toast.makeText(this, "需要更新? ${updateCallBean.update}", Toast.LENGTH_SHORT).show()

    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
