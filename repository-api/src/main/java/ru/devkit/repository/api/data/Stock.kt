package ru.devkit.repository.api.data

data class Stock(
    val id: String,
    val name: String,
    val currentPrice: Double,
    val lastPrice: Double
)
