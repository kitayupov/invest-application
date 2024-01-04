package ru.devkit.feature.stock.history

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.devkit.feature.stock.history.data.InvestmentUiModel
import ru.devkit.feature.stock.history.databinding.FragmentStockHistoryBinding
import ru.devkit.feature.stock.history.di.DaggerStockHistoryComponent
import ru.devkit.feature.stock.history.di.StockHistoryComponentDependenciesProvider
import ru.devkit.feature.stock.history.ui.ChartMarkerView
import ru.devkit.ui.ListItemComponent
import ru.devkit.ui.utils.getColorResCompat
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class StockHistoryFragment : Fragment() {

    @Inject
    lateinit var viewModel: StockHistoryViewModel

    private lateinit var binding: FragmentStockHistoryBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as StockHistoryComponentDependenciesProvider)
            .stockHistoryComponentDependencies()
        DaggerStockHistoryComponent.factory().create(dependencies).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStockHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val symbol = arguments?.getString("symbol") ?: return
        viewModel.attach(symbol)
        setupChart()
    }

    override fun onResume() {
        super.onResume()
        setupDataUpdate()
    }

    override fun onPause() {
        super.onPause()
        viewModel.detach()
    }

    private fun setupDataUpdate() {
        lifecycleScope.launch {
            viewModel.model.collectLatest(::updateData)
        }
        lifecycleScope.launch {
            viewModel.ticks.collectLatest(::updateTicks)
        }
    }

    private fun updateData(model: InvestmentUiModel) {
        binding.investmentTitle.setName(model.name)
        binding.investmentTitle.setData(
            ListItemComponent.Data(
                value = model.value,
                diffValue = model.diffValue,
                diffPercentage = model.diffPercentage,
                diffColor = model.diffSign.color
            )
        )
    }

    private fun updateTicks(ticks: List<Double>) {
        val entries = ticks.mapIndexed { index, value -> Entry(index.toFloat(), value.toFloat()) }
        binding.stockHistoryChart.run {
            data = LineData().apply {
                addDataSet(createDataSet(entries))
                moveViewToX(entryCount.toFloat())
                setVisibleXRangeMaximum(20f)
                invalidate()
            }
        }
    }

    private fun createDataSet(entries: List<Entry>): LineDataSet {
        return LineDataSet(entries, "").apply {
            color = requireContext().getColor(R.color.design_default_color_secondary)
            setCircleColor(color)
            setDrawValues(false)
            lineWidth = 2f
            circleRadius = 4f
            highlightLineWidth = 2f
            enableDashedHighlightLine(2f, 2f, 0f)
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        }
    }

    private fun setupChart() {
        binding.stockHistoryChart.apply {
            isScaleYEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            val textColor = context.getColorResCompat(android.R.attr.textColorSecondary)
            xAxis.textColor = textColor
            axisLeft.textColor = textColor
            axisRight.textColor = textColor
            ChartMarkerView(requireContext()).setChart(this)
            animateX(500)
        }
    }
}
