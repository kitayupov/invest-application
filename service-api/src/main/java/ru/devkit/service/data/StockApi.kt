package ru.devkit.service.data

/**
 * @author k.i.tayupov
 */
data class StockApi(
    val symbol: String,
    val name: String,
    val currentPrice: Double,
    val lastPrice: Double,
)
