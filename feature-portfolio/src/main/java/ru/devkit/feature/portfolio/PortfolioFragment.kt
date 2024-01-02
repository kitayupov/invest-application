package ru.devkit.feature.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.launch
import ru.devkit.feature.portfolio.adapter.PortfolioAdapter
import ru.devkit.feature.portfolio.databinding.FragmentPortfolioBinding
import ru.devkit.feature.portfolio.di.PortfolioComponent
import ru.devkit.ui.ListItemComponent
import ru.devkit.ui.model.PortfolioUiModel
import javax.inject.Inject

class PortfolioFragment : Fragment() {

    @Inject
    lateinit var viewModel: PortfolioViewModel

    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var portfolioAdapter: PortfolioAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as PortfolioComponent).inject(this)
        setupRecyclerView()
        setupDataUpdate()
    }

    private fun setupRecyclerView() {
        portfolioAdapter = PortfolioAdapter()
        binding.recyclerView.apply {
            adapter = portfolioAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupDataUpdate() {
        lifecycleScope.launch {
            viewModel.model.collect(::updateData)
        }
    }

    private fun updateData(model: PortfolioUiModel) {
        portfolioAdapter.submitList(model.items)
        binding.portfolioTitle.setData(
            ListItemComponent.Data(
                value = model.totalValue,
                diffValue = model.totalDiffValue,
                diffPercentage = model.totalDiffPercentage,
                diffColor = model.totalDiffSign.color
            )
        )
    }

    companion object {
        fun newInstance() = PortfolioFragment()
    }
}
