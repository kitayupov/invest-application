package ru.devkit.feature.summary

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch
import ru.devkit.common.di.ComponentDependenciesProvider
import ru.devkit.feature.summary.data.SummaryItemUiModel
import ru.devkit.feature.summary.data.SummaryUiModel
import ru.devkit.feature.summary.databinding.FragmentSummaryBinding
import ru.devkit.feature.summary.di.DaggerSummaryComponent
import ru.devkit.feature.summary.di.SummaryComponentDependencies
import ru.devkit.ui.utils.getColorResCompat
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
internal class SummaryFragment : Fragment() {

    @Inject
    lateinit var viewModel: SummaryViewModel

    private lateinit var binding: FragmentSummaryBinding

    private var selectedItemId = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependencies = (context.applicationContext as ComponentDependenciesProvider)
            .componentDependencies()
        DaggerSummaryComponent.factory()
            .create(dependencies as SummaryComponentDependencies)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.attach()
        setupDataUpdate()
    }

    override fun onPause() {
        super.onPause()
        viewModel.detach()
    }

    private fun setupDataUpdate() {
        lifecycleScope.launch {
            viewModel.model.collect(::updateData)
        }
    }

    private fun updateData(model: SummaryUiModel) {
        binding.summaryTitle.valueTextView.text = model.totalValue
        binding.summaryChart.apply {
            data = PieData().apply { addDataSet(createDataSet(model.items)) }
            data.setDrawValues(false)
            updateCenterText(model.items.find { it.symbol == selectedItemId })
            invalidate()
        }
    }

    private fun createDataSet(items: List<SummaryItemUiModel>): IPieDataSet {
        val entries = items.map {
            PieEntry(it.percentage, it.percentageString, it)
        }
        return PieDataSet(entries, "").apply {
            colors = ColorTemplate.MATERIAL_COLORS.toList()
        }
    }

    private fun setupChart() {
        binding.summaryChart.apply {
            legend.isEnabled = false
            description.isEnabled = false
            setOnChartValueSelectedListener(onChartValueSelectedListener)
            setHoleColor(Color.TRANSPARENT)
            setCenterTextOffset(0f, 8f)
            setCenterTextColor(context.getColorResCompat(android.R.attr.textColorPrimary))
            setCenterTextTypeface(Typeface.DEFAULT_BOLD)
            setCenterTextSize(18f)
            animateX(500)
        }
    }

    private val onChartValueSelectedListener = object : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry, ignore: Highlight) {
            updateCenterText(e.data as? SummaryItemUiModel)
        }

        override fun onNothingSelected() {
            updateCenterText(null)
        }
    }

    private fun updateCenterText(model: SummaryItemUiModel?) {
        with(binding.summaryChart) {
            if (model != null) {
                selectedItemId = model.symbol
                centerText = "${model.name}\n\n${model.value}"
            } else {
                selectedItemId = ""
                centerText = ""
            }
        }
    }
}
