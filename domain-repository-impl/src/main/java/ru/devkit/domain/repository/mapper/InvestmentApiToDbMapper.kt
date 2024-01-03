package ru.devkit.domain.repository.mapper

import ru.devkit.domain.repository.db.data.InvestmentDb
import ru.devkit.service.data.InvestmentApi
import ru.devkit.service.data.StockApi
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class InvestmentApiToDbMapper @Inject constructor() {

    operator fun invoke(from: InvestmentApi, stock: StockApi): InvestmentDb {
        return InvestmentDb(
            symbol = from.symbol,
            name = stock.name,
            quantity = from.quantity,
            currentPrice = stock.currentPrice,
            firstPrice = from.initialPrice,
            lastPrice = stock.lastPrice
        )
    }
}
