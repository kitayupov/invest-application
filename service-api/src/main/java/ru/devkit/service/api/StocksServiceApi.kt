package ru.devkit.service.api

import ru.devkit.service.api.data.StockApi

interface StocksServiceApi {
    fun getStock(id: String): StockApi
    fun getStockHistory(id: String): List<Double>
    fun release()
}
