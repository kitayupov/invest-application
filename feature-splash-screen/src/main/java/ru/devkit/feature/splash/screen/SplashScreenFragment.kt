package ru.devkit.feature.splash.screen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import ru.devkit.feature.splash.screen.databinding.FragmentSplashScreenBinding
import ru.devkit.ui.R

/**
 * @author k.i.tayupov
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentSplashScreenBinding.inflate(inflater, container, false).root
    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
        setupTransition()
    }

    private fun setupNavigation() {
        Handler().postDelayed({
            findNavController().navigate(ru.devkit.common.navigation.R.id.action_splashScreenFragment_to_portfolioFragment)
        }, 2000)
    }

    private fun setupTransition() {
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}
