package ru.devkit.feature.stock.history.data

import ru.devkit.ui.model.DiffSign

/**
 * @author k.i.tayupov
 */
internal data class InvestmentUiModel(
    val name: String,
    val value: String,
    val diffValue: String,
    val diffPercentage: String,
    val diffSign: DiffSign,
) {

    companion object {
        val EMPTY = InvestmentUiModel(
            name = "",
            value = "",
            diffValue = "",
            diffPercentage = "",
            diffSign = DiffSign.NONE
        )
    }
}
