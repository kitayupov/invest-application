package ru.devkit.utils

fun Double.isPositive(): Boolean {
    return this > 1e-8
}

fun Double.isNegative(): Boolean {
    return this < -1e-8
}
