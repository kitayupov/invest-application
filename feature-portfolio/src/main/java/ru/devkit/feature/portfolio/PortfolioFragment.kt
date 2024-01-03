package ru.devkit.feature.portfolio

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.launch
import ru.devkit.feature.portfolio.adapter.PortfolioAdapter
import ru.devkit.feature.portfolio.databinding.FragmentPortfolioBinding
import ru.devkit.feature.portfolio.di.PortfolioComponent
import ru.devkit.ui.ListItemComponent
import ru.devkit.ui.model.PortfolioUiModel
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class PortfolioFragment : Fragment() {

    @Inject
    lateinit var viewModel: PortfolioViewModel

    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var portfolioAdapter: PortfolioAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as PortfolioComponent).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupDataUpdate()
        setupNavigation()
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

    private fun setupNavigation() {
        binding.summaryButton.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://ru.devkit.invest.application/summary_fragment".toUri())
                .build()
            findNavController().navigate(request)
        }
        portfolioAdapter.onClickAction = { symbol ->
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://ru.devkit.invest.application/stock_history_fragment/${symbol}".toUri())
                .build()
            findNavController().navigate(request)
        }
    }

    companion object {
        fun newInstance() = PortfolioFragment()
    }
}
