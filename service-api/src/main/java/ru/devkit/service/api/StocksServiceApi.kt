package ru.devkit.service.api

import ru.devkit.service.api.data.StockApi

/**
 * @author k.i.tayupov
 */
interface StocksServiceApi {

    fun getStock(symbol: String): StockApi

    fun getStockHistory(symbol: String): List<Double>
}
