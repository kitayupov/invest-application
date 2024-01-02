package ru.devkit.investapplication.di

import dagger.Component
import ru.devkit.feature.portfolio.di.PortfolioModule
import ru.devkit.feature.stock.history.di.StockHistoryModule
import ru.devkit.feature.summary.di.SummaryModule
import ru.devkit.investapplication.MainActivity
import ru.devkit.service.impl.di.ServiceModule

/**
 * @author k.i.tayupov
 */
@Component(
    modules = [
        AppModule::class,
        PortfolioModule::class,
        StockHistoryModule::class,
        SummaryModule::class,
        ServiceModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
