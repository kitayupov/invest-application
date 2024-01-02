package ru.devkit.service.api.data

/**
 * @author k.i.tayupov
 */
data class StockApi(
    val id: String,
    val name: String,
    val currentPrice: Double,
    val lastPrice: Double,
)
