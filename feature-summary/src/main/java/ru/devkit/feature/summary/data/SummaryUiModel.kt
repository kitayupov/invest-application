package ru.devkit.feature.summary.data

data class SummaryUiModel(
    val items: List<SummaryItemUiModel>,
    val totalValue: String
) {
    companion object {
        val EMPTY = SummaryUiModel(items = emptyList(), totalValue = "")
    }
}
