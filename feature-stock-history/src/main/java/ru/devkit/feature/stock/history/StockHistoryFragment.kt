package ru.devkit.feature.stock.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author k.i.tayupov
 */
class StockHistoryFragment : Fragment() {

    private lateinit var viewModel: StockHistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_stock_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = StockHistoryFragment()
    }
}
