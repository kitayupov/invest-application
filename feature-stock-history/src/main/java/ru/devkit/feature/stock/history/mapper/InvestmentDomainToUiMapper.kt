package ru.devkit.feature.stock.history.mapper

import ru.devkit.domain.repository.data.Investment
import ru.devkit.feature.stock.history.data.InvestmentUiModel
import ru.devkit.ui.model.DiffSign
import ru.devkit.utils.formatCurrencyPrice
import ru.devkit.utils.formatPercentage
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class InvestmentDomainToUiMapper @Inject constructor() {

    operator fun invoke(from: Investment): InvestmentUiModel {
        return InvestmentUiModel(
            name = from.name,
            value = formatCurrencyPrice(from.value),
            diffValue = formatCurrencyPrice(from.difference),
            diffPercentage = formatPercentage(from.difference / from.initialValue * 100),
            diffSign = DiffSign.get(from.difference)
        )
    }
}
