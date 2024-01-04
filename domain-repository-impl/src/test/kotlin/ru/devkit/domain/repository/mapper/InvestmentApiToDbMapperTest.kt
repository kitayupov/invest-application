package ru.devkit.domain.repository.mapper

import ru.devkit.domain.repository.db.data.InvestmentDb
import ru.devkit.service.data.InvestmentApi
import ru.devkit.service.data.StockApi
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author k.i.tayupov
 */
class InvestmentApiToDbMapperTest {

    @Test
    fun `should map api model to db model`() {
        val apiModel = InvestmentApi(SYMBOL, QUANTITY, FIRST_PRICE)
        val stockApiModel = StockApi(SYMBOL, NAME, CURRENT_PRICE, LAST_PRICE)

        val mapper = InvestmentApiToDbMapper()
        val actual: InvestmentDb = mapper(apiModel, stockApiModel)

        assertEquals(SYMBOL, actual.symbol)
        assertEquals(NAME, actual.name)
        assertEquals(QUANTITY, actual.quantity)
        assertEquals(CURRENT_PRICE, actual.currentPrice)
        assertEquals(FIRST_PRICE, actual.firstPrice)
        assertEquals(LAST_PRICE, actual.lastPrice)
    }

    companion object {
        const val SYMBOL = "symbol"
        const val NAME = "name"
        const val QUANTITY = 8
        const val CURRENT_PRICE = 20.0
        const val FIRST_PRICE = 13.0
        const val LAST_PRICE = 21.0
    }
}
