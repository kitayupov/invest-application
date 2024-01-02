package ru.devkit.service.api

import ru.devkit.service.api.data.StockApi

/**
 * @author k.i.tayupov
 */
interface StocksServiceApi {

    fun getStock(id: String): StockApi

    fun getStockHistory(id: String): List<Double>
}
