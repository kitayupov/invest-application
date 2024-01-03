package ru.devkit.feature.portfolio.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.devkit.feature.portfolio.databinding.ListItemPortfolioBinding
import ru.devkit.ui.ListItemComponent
import ru.devkit.ui.model.*
import ru.devkit.ui.model.ListItemUiModel

/**
 * @author k.i.tayupov
 */
internal class PortfolioAdapter: ListAdapter<ListItemUiModel, PortfolioAdapter.InvestmentViewHolder>(PortfolioDiffCallback()) {

    var onClickAction: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestmentViewHolder {
        val binding = ListItemPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvestmentViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: InvestmentViewHolder, position: Int, payloads: MutableList<Any>) {
        val investment = currentList[position]
        if (payloads.isEmpty()) {
            holder.bind(investment)
        } else {
            holder.bind(payloads.first() as Bundle)
        }
    }

    override fun onBindViewHolder(holder: InvestmentViewHolder, position: Int) {
        val investment = currentList[position]
        holder.bind(investment)
    }

    inner class InvestmentViewHolder(private val binding: ListItemComponent) : ViewHolder(binding) {
        fun bind(model: ListItemUiModel) = binding.run {
            setName(model.name)
            setData(
                ListItemComponent.Data(
                    value = model.value,
                    diffValue = model.diffValue,
                    diffPercentage = model.diffPercentage,
                    diffColor = model.diffSign.color
                )
            )
            setOnClickListener { onClickAction(model.id) }
        }

        fun bind(bundle: Bundle) = binding.run {
            bundle.getString(ARG_VALUE)?.let { valueTextView.text = it }
            if (bundle.containsKey(ARG_DIFF_VALUE)) {
                diffTextView.text = "${bundle.getString(ARG_DIFF_VALUE)} (${bundle.getString(ARG_DIFF_PERCENTAGE)})"
                diffTextView.setTextColor(bundle.getInt(ARG_DIFF_SIGN))
            }
            binding.setSign(icon = bundle.getInt(ARG_LAST_SIGN), animated = true)
        }
    }
}
