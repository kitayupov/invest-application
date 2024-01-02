package ru.devkit.utils

/**
 * @author k.i.tayupov
 */
fun Double.isPositive(): Boolean {
    return this > 1e-8
}

fun Double.isNegative(): Boolean {
    return this < -1e-8
}
