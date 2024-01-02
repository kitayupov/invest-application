package ru.devkit.investapplication.app

import android.app.Application
import ru.devkit.feature.portfolio.PortfolioFragment
import ru.devkit.feature.portfolio.di.PortfolioComponent
import ru.devkit.feature.summary.SummaryFragment
import ru.devkit.feature.summary.di.SummaryComponent
import ru.devkit.investapplication.di.AppComponent
import ru.devkit.investapplication.di.AppModule
import ru.devkit.investapplication.di.DaggerAppComponent

/**
 * @author k.i.tayupov
 */
class App : Application(), PortfolioComponent, SummaryComponent {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }

    override fun inject(fragment: PortfolioFragment) {
        appComponent.inject(fragment)
    }

    override fun inject(fragment: SummaryFragment) {
        appComponent.inject(fragment)
    }
}
