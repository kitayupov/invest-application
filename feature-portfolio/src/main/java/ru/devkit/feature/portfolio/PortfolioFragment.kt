package ru.devkit.feature.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.devkit.feature.portfolio.di.PortfolioComponent
import javax.inject.Inject

class PortfolioFragment : Fragment() {

    @Inject
    lateinit var viewModel: PortfolioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as PortfolioComponent).inject(this)
        System.err.println("portfolio: ${viewModel.model.value}")
    }

    companion object {
        fun newInstance() = PortfolioFragment()
    }
}
