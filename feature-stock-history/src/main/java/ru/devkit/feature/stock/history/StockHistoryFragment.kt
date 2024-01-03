package ru.devkit.feature.stock.history

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.devkit.feature.stock.history.di.StockHistoryComponent
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class StockHistoryFragment : Fragment() {

    @Inject
    lateinit var viewModel: StockHistoryViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as StockHistoryComponent).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_stock_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val symbol = arguments?.getString("symbol") ?: return
        viewModel.attach(symbol)
    }

    companion object {
        fun newInstance() = StockHistoryFragment()
    }
}
