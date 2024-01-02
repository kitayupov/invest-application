package ru.devkit.feature.summary.data

/**
 * @author k.i.tayupov
 */
data class SummaryUiModel(
    val items: List<SummaryItemUiModel>,
    val totalValue: String,
) {
    companion object {
        val EMPTY = SummaryUiModel(items = emptyList(), totalValue = "")
    }
}
