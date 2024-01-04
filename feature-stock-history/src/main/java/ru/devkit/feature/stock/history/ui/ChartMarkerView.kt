package ru.devkit.feature.stock.history.ui

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import ru.devkit.feature.stock.history.R
import ru.devkit.utils.formatPrice

internal class ChartMarkerView(context: Context) : MarkerView(context, R.layout.layout_marker_view) {

    private val content: TextView = findViewById(R.id.marker_text)

    fun setChart(chart: LineChart) {
        chart.marker = this
        chartView = chart
    }

    override fun refreshContent(e: Entry, highlight: Highlight) {
        content.text = formatPrice(e.y)
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width.toFloat() / 2), -height.toFloat())
    }
}
