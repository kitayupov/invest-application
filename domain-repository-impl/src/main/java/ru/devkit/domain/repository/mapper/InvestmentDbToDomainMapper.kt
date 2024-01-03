package ru.devkit.domain.repository.mapper

import ru.devkit.domain.repository.data.Investment
import ru.devkit.domain.repository.db.data.InvestmentDb
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class InvestmentDbToDomainMapper @Inject constructor() {

    operator fun invoke(from: InvestmentDb): Investment {
        return Investment(
            symbol = from.symbol,
            name = from.name,
            quantity = from.quantity,
            currentPrice = from.currentPrice,
            firstPrice = from.firstPrice,
            lastPrice = from.lastPrice
        )
    }
}
