package ru.devkit.ui.model

const val ARG_VALUE = "arg_value"
const val ARG_DIFF_VALUE = "arg_diff_value"
const val ARG_DIFF_PERCENTAGE = "arg_diff_percentage"
const val ARG_DIFF_SIGN = "arg_diff_sign"
const val ARG_LAST_SIGN = "arg_last_sign"

/**
 * @author k.i.tayupov
 */
data class ListItemUiModel(
    val symbol: String,
    val name: String,
    val value: String,
    val diffValue: String,
    val diffPercentage: String,
    val diffSign: DiffSign,
    val lastSign: LastSign,
)
