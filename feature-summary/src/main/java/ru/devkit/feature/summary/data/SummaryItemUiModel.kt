package ru.devkit.feature.summary.data

/**
 * @author k.i.tayupov
 */
internal data class SummaryItemUiModel(
    val symbol: String,
    val name: String,
    val value: String,
    val percentage: Float,
    val percentageString: String,
)
