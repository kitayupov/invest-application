package ru.devkit.domain.repository.mapper

import ru.devkit.domain.repository.data.Investment
import ru.devkit.service.api.data.InvestmentApi
import ru.devkit.service.api.data.StockApi
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class InvestmentApiToDomainMapper @Inject constructor() {

    operator fun invoke(from: InvestmentApi, stock: StockApi): Investment {
        return Investment(
            id = from.id,
            name = stock.name,
            quantity = from.quantity,
            currentPrice = stock.currentPrice,
            firstPrice = from.initialPrice,
            lastPrice = stock.lastPrice
        )
    }
}