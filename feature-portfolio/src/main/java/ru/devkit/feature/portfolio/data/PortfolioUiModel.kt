package ru.devkit.feature.portfolio.data

import ru.devkit.ui.model.DiffSign
import ru.devkit.ui.model.ListItemUiModel

/**
 * @author k.i.tayupov
 */
internal data class PortfolioUiModel(
    val items: List<ListItemUiModel>,
    val totalValue: String,
    val totalDiffValue: String,
    val totalDiffPercentage: String,
    val totalDiffSign: DiffSign
) {

    companion object {
        val EMPTY = PortfolioUiModel(
            items = emptyList(),
            totalValue = "",
            totalDiffValue = "",
            totalDiffPercentage = "",
            totalDiffSign = DiffSign.NONE
        )
    }
}
