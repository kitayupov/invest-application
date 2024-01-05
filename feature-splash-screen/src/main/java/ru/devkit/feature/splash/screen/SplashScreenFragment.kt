package ru.devkit.feature.splash.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.devkit.feature.splash.screen.databinding.FragmentSplashScreenBinding

/**
 * @author k.i.tayupov
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentSplashScreenBinding.inflate(inflater, container, false).root
    }
}
