package ru.devkit.domain.repository.data

/**
 * @author k.i.tayupov
 */
data class Investment(
    val id: String,
    val name: String,
    val quantity: Int,
    val currentPrice: Double,
    val firstPrice: Double,
    val lastPrice: Double,
) {
    val value = currentPrice * quantity
    val difference = (currentPrice - firstPrice) * quantity
    val lastDifference = currentPrice - lastPrice
    val initialValue = firstPrice * quantity
}
