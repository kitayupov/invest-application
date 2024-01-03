package ru.devkit.service.mock

import ru.devkit.service.StocksServiceApi
import ru.devkit.service.data.StockApi
import ru.devkit.service.mock.data.MockData
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread
import kotlin.random.Random

/**
 * @author k.i.tayupov
 */
@Singleton
class MockStocksService @Inject constructor(
    private val commonService: MockCommonService,
) : StocksServiceApi {

    private val cache = AtomicReference(mutableMapOf<String, List<Double>>())

    private var isRunning = true

    init {
        thread {
            while (isRunning) {
                val update = cache.get().mapValues { (_, value) ->
                    if (Random.nextInt(5) == 0) {
                        val last = value.last()
                        value + (last + offset(last))
                    } else {
                        value
                    }
                }
                cache.set(update.toMutableMap())
                Thread.sleep(1_000)
            }
        }
    }

    override fun getStock(symbol: String): StockApi {
        val stock = findDto(symbol)
        val history = getStockHistory(symbol)
        return StockApi(
            symbol = stock.id,
            name = stock.name,
            currentPrice = history.last(),
            lastPrice = history.dropLast(1).last()
        )
    }

    override fun getStockHistory(symbol: String): List<Double> {
        val history = cache.get()
        if (history.containsKey(symbol).not()) {
            val first = findDto(symbol).price
            history[symbol] = List(20) { first + offset(first) }
        }
        return history[symbol] ?: throw IllegalArgumentException("ID $symbol was not found")
    }

    fun release() {
        isRunning = false
    }

    private fun findDto(id: String): MockData {
        return commonService.dto.find { it.id == id } ?: throw IllegalArgumentException("ID $id was not found")
    }

    private fun offset(value: Double) = Random.nextDouble(-value * 0.1, value * 0.1)
}
