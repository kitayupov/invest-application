package ru.devkit.feature.portfolio.mapper

import ru.devkit.domain.repository.data.Portfolio
import ru.devkit.ui.model.DiffSign
import ru.devkit.ui.model.LastSign
import ru.devkit.ui.model.ListItemUiModel
import ru.devkit.ui.model.PortfolioUiModel
import ru.devkit.utils.formatCurrencyPrice
import ru.devkit.utils.formatPercentage
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class PortfolioDomainToUiMapper @Inject constructor() {

    operator fun invoke(from: Portfolio): PortfolioUiModel {
        val items = from.investments.map {
            ListItemUiModel(
                symbol = it.symbol,
                name = it.name,
                value = formatCurrencyPrice(it.value),
                diffValue = formatCurrencyPrice(it.difference),
                diffPercentage = formatPercentage(it.difference / it.initialValue * 100),
                diffSign = DiffSign.get(it.difference),
                lastSign = LastSign.get(it.lastDifference),
            )
        }
        return PortfolioUiModel(
            items = items,
            totalValue = formatCurrencyPrice(from.totalValue),
            totalDiffValue = formatCurrencyPrice(from.totalDifference),
            totalDiffPercentage = formatPercentage(from.totalDifference / from.totalInitialValue * 100),
            totalDiffSign = DiffSign.get(from.totalDifference),
        )
    }
}
