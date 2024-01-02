package ru.devkit.feature.portfolio.adapter

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import ru.devkit.ui.model.*
import ru.devkit.ui.model.ListItemUiModel

class PortfolioDiffCallback : DiffUtil.ItemCallback<ListItemUiModel>() {

    override fun areItemsTheSame(oldItem: ListItemUiModel, newItem: ListItemUiModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ListItemUiModel, newItem: ListItemUiModel): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ListItemUiModel, newItem: ListItemUiModel): Any {
        return Bundle().apply {
            if (oldItem.value != newItem.value) {
                putString(ARG_VALUE, newItem.value)
                putString(ARG_DIFF_VALUE, newItem.diffValue)
                putString(ARG_DIFF_PERCENTAGE, newItem.diffPercentage)
                putInt(ARG_DIFF_SIGN, newItem.diffSign.color)
            }
            putInt(ARG_LAST_SIGN, newItem.lastSign.icon)
        }
    }
}
