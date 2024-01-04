package ru.devkit.feature.summary.data

/**
 * @author k.i.tayupov
 */
internal data class SummaryUiModel(
    val items: List<SummaryItemUiModel>,
    val totalValue: String,
) {
    companion object {
        val EMPTY = SummaryUiModel(items = emptyList(), totalValue = "")
    }
}
