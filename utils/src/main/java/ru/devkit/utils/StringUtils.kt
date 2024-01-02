package ru.devkit.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * @author k.i.tayupov
 */
fun formatCurrencyPrice(value: Number): String {
    return "$ " + formatPrice(value)
}

fun formatPrice(value: Number): String {
    val symbols = DecimalFormatSymbols().apply { groupingSeparator = ' ' }
    return DecimalFormat("#,###.00", symbols).format(value)
}

fun formatPercentage(value: Number): String {
    return String.format("%.2f %%", value)
}
