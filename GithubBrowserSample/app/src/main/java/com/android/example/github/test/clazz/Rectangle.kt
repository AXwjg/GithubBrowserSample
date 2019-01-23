package com.android.example.github.test.clazz

/**
 * @author oldwang
 * @date 2019/1/17
 * Description:
 */
open class Rectangle(val height: Double, val length: Double)
    : Shape(listOf(height, length, height, length)), RectangleProperties {

    override val isShape: Boolean get() = length == height

    override fun calculateArea(): Double? = height * length
}