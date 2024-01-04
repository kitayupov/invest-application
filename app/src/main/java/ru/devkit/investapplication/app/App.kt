package ru.devkit.investapplication.app

import android.app.Application
import ru.devkit.feature.portfolio.di.PortfolioComponentDependencies
import ru.devkit.feature.portfolio.di.PortfolioComponentDependenciesProvider
import ru.devkit.feature.stock.history.StockHistoryFragment
import ru.devkit.feature.stock.history.di.StockHistoryComponent
import ru.devkit.feature.summary.SummaryFragment
import ru.devkit.feature.summary.di.SummaryComponent
import ru.devkit.investapplication.di.AppComponent
import ru.devkit.investapplication.di.AppModule
import ru.devkit.investapplication.di.DaggerAppComponent

/**
 * @author k.i.tayupov
 */
class App : Application(), PortfolioComponentDependenciesProvider, StockHistoryComponent, SummaryComponent {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }

    override fun inject(fragment: StockHistoryFragment) {
        appComponent.inject(fragment)
    }

    override fun inject(fragment: SummaryFragment) {
        appComponent.inject(fragment)
    }

    override fun portfolioComponentDependencies(): PortfolioComponentDependencies {
        return appComponent
    }
}
