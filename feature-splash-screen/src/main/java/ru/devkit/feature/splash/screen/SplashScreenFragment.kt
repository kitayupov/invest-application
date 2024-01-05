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
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import ru.devkit.feature.splash.screen.databinding.FragmentSplashScreenBinding

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
    }

    private fun setupNavigation() {
        Handler().postDelayed({
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://ru.devkit.invest.application/portfolio_fragment".toUri())
                .build()
            findNavController().navigate(request)
        }, 2000)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(ru.devkit.ui.R.transition.fade)
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
