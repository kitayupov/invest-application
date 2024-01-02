package ru.devkit.repository.api

import ru.devkit.repository.api.data.Stock

interface StocksApi {
    fun getStock(id: String): Stock
    fun getStockHistory(id: String): List<Double>
    fun release()
}
