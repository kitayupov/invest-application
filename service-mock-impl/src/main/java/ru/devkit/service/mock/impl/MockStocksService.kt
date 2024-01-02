package ru.devkit.service.mock.impl

import ru.devkit.service.api.StocksServiceApi
import ru.devkit.service.api.data.Stock
import ru.devkit.service.mock.impl.data.MockData
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import kotlin.concurrent.thread
import kotlin.random.Random

class MockStocksService @Inject constructor(
    private val commonService: MockCommonService
): StocksServiceApi {

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

    override fun getStock(id: String): Stock {
        val stock = findDto(id)
        val history = getStockHistory(id)
        return Stock(
            id = stock.id,
            name = stock.name,
            currentPrice = history.last(),
            lastPrice = history.dropLast(1).last()
        )
    }

    override fun getStockHistory(id: String): List<Double> {
        val history = cache.get()
        if (history.containsKey(id).not()) {
            val first = findDto(id).price
            history[id] = List(20) { first + offset(first) }
        }
        return history[id] ?: throw IllegalArgumentException("ID $id was not found")
    }

    override fun release() {
        isRunning = false
    }

    private fun findDto(id: String): MockData {
        return commonService.dto.find { it.id == id } ?: throw IllegalArgumentException("ID $id was not found")
    }

    private fun offset(value: Double) = Random.nextDouble(-value * 0.1, value * 0.1)
}
