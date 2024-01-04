package ru.devkit.feature.summary.mapper

import ru.devkit.domain.repository.data.Portfolio
import ru.devkit.feature.summary.data.SummaryItemUiModel
import ru.devkit.feature.summary.data.SummaryUiModel
import ru.devkit.utils.formatCurrencyPrice
import ru.devkit.utils.formatPercentage
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
internal class SummaryDomainToUiMapper @Inject constructor() {

    operator fun invoke(from: Portfolio): SummaryUiModel {
        val items = from.investments.map {
            val percentage = it.value / from.totalValue
            SummaryItemUiModel(
                symbol = it.symbol,
                name = it.name,
                value = formatCurrencyPrice(it.value),
                percentage = (it.value / from.totalValue).toFloat(),
                percentageString = formatPercentage(percentage * 100)
            )
        }
        return SummaryUiModel(
            items = items,
            totalValue = formatCurrencyPrice(from.totalValue)
        )
    }
}
