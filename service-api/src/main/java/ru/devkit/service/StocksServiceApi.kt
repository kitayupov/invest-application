package ru.devkit.service

import ru.devkit.service.data.StockApi

/**
 * @author k.i.tayupov
 */
interface StocksServiceApi {

    fun getStock(symbol: String): StockApi

    fun getStockHistory(symbol: String): List<Double>
}
