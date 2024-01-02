package ru.devkit.service.api

import ru.devkit.service.api.data.Stock

interface StocksServiceApi {
    fun getStock(id: String): Stock
    fun getStockHistory(id: String): List<Double>
    fun release()
}
