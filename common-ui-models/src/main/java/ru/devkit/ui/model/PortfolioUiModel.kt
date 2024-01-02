package ru.devkit.ui.model

/**
 * @author k.i.tayupov
 */
data class PortfolioUiModel(
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
