package ru.devkit.feature.portfolio

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch
import ru.devkit.common.di.ComponentDependenciesProvider
import ru.devkit.common.navigation.R as navigationR
import ru.devkit.feature.portfolio.adapter.PortfolioAdapter
import ru.devkit.feature.portfolio.databinding.FragmentPortfolioBinding
import ru.devkit.feature.portfolio.di.DaggerPortfolioComponent
import ru.devkit.ui.ListItemComponent
import ru.devkit.feature.portfolio.data.PortfolioUiModel
import ru.devkit.feature.portfolio.di.PortfolioComponentDependencies
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
internal class PortfolioFragment : Fragment() {

    @Inject
    lateinit var viewModel: PortfolioViewModel

    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var portfolioAdapter: PortfolioAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as ComponentDependenciesProvider)
            .componentDependencies()
        DaggerPortfolioComponent.factory()
            .create(dependencies as PortfolioComponentDependencies)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupNavigation()
        setupTransition()
        setupDataUpdate()
        viewModel.attach()

    }

    override fun onPause() {
        super.onPause()
        viewModel.detach()
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
            viewModel.model
                .dropWhile { it == PortfolioUiModel.EMPTY }
                .collect(::updateData)
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
        with(findNavController()) {
            portfolioAdapter.onClickAction = { symbol ->
                navigate(navigationR.id.action_portfolioFragment_to_stockHistoryFragment, bundleOf("symbol" to symbol))
            }
            binding.summaryButton.setOnClickListener {
                navigate(navigationR.id.action_portfolioFragment_to_summaryFragment)
            }
        }
    }

    private fun setupTransition() {
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(navigationR.transition.fade)
    }
}
