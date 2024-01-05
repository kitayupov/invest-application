package ru.devkit.feature.splash.screen

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.BounceInterpolator
import androidx.navigation.fragment.findNavController
import ru.devkit.common.navigation.R as navigationR
import ru.devkit.feature.splash.screen.databinding.FragmentSplashScreenBinding

/**
 * @author k.i.tayupov
 */

private const val ANIMATION_DELAY = 500L
private const val ANIMATION_DURATION = 2_000L
private const val TRANSITION_DELAY = 1_000L

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupAnimation()
        invokeNavigation()
        setupTransition()
    }

    private fun setupAnimation() {
        ObjectAnimator.ofFloat(binding.icon, View.ALPHA, 0f, 1f).apply {
            interpolator = BounceInterpolator()
            startDelay = ANIMATION_DELAY
            duration = ANIMATION_DURATION
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                }
            })
        }.start()
    }

    private fun invokeNavigation() {
        Handler().postDelayed({
            findNavController().navigate(navigationR.id.action_splashScreenFragment_to_portfolioFragment)
        }, ANIMATION_DURATION + TRANSITION_DELAY)
    }

    private fun setupTransition() {
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(navigationR.transition.fade)
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
