package ru.devkit.service.api.data

/**
 * @author k.i.tayupov
 */
data class InvestmentApi(
    val symbol: String,
    val quantity: Int,
    val initialPrice: Double,
)
