package ru.devkit.feature.summary.data

/**
 * @author k.i.tayupov
 */
data class SummaryItemUiModel(
    val id: String,
    val name: String,
    val value: String,
    val percentage: Float,
    val percentageString: String,
)
