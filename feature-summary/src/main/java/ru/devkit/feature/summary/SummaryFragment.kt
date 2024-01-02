package ru.devkit.feature.summary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.devkit.feature.summary.di.SummaryComponent
import javax.inject.Inject

class SummaryFragment : Fragment() {

    @Inject
    lateinit var viewModel: SummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as SummaryComponent).inject(this)
        System.err.println("Summary: ${viewModel.model}")
    }

    companion object {
        fun newInstance() = SummaryFragment()
    }
}
