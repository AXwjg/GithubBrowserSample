package com.android.example.github.test.clazz

/**
 * @author oldwang
 * @date 2019/1/17
 * Description:
 */
class Triangle(private var sideA: Double,
               private var sideB: Double,
               private var sideC: Double) :Shape(listOf(sideA, sideB, sideC)) {

    private var add = 0.0
    override fun calculateArea(): Double? {
        val value = perimeter / 2
        return Math.sqrt(value * (value - sideA) * (value - sideB) * (value - sideC))
    }
}