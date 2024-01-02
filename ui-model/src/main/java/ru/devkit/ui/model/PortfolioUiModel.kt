package ru.devkit.ui.model

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
