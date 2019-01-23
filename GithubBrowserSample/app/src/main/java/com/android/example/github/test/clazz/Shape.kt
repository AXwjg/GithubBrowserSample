package com.android.example.github.test.clazz

/**
 * @author oldwang
 * @date 2019/1/17
 * Description:
 */
abstract class Shape(val sides: List<Double>) {

    val perimeter: Double get() = sides.sum()

    abstract fun calculateArea(): Double?
}