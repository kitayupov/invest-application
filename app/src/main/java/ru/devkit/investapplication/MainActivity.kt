package ru.devkit.investapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.devkit.feature.portfolio.PortfolioFragment
import ru.devkit.investapplication.app.App

/**
* @author k.i.tayupov
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)
    }
}
