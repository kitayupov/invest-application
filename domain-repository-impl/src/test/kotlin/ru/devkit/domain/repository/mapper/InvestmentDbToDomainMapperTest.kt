package ru.devkit.domain.repository.mapper

import ru.devkit.domain.repository.data.Investment
import ru.devkit.domain.repository.db.data.InvestmentDb
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author k.i.tayupov
 */
class InvestmentDbToDomainMapperTest {

    @Test
    fun `should map db model to domain model`() {
        val dbModel = InvestmentDb(SYMBOL, NAME, QUANTITY, CURRENT_PRICE, FIRST_PRICE, LAST_PRICE)

        val mapper = InvestmentDbToDomainMapper()
        val actual: Investment = mapper(dbModel)

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
