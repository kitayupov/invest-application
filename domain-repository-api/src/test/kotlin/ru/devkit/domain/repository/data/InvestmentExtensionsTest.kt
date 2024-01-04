package ru.devkit.domain.repository.data

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author k.i.tayupov
 */
class InvestmentExtensionsTest {

    @Test
    fun `should return model extensions`() {
        val model = Investment(
            symbol = SYMBOL,
            name = NAME,
            quantity = 8,
            currentPrice = 20.0,
            firstPrice = 13.0,
            lastPrice = 21.0
        )

        assertEquals(160.0, model.value)
        assertEquals(56.0, model.difference)
        assertEquals(-1.0, model.lastDifference)
        assertEquals(104.0, model.initialValue)
    }

    companion object {
        const val SYMBOL = "symbol"
        const val NAME = "name"
    }
}
