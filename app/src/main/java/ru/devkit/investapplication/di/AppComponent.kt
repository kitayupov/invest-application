package ru.devkit.investapplication.di

import dagger.Component
import ru.devkit.domain.repository.PortfolioRepository
import ru.devkit.domain.repository.di.RepositoryModule
import ru.devkit.feature.portfolio.di.PortfolioComponentDependencies
import ru.devkit.feature.stock.history.di.StockHistoryComponentDependencies
import ru.devkit.feature.summary.di.SummaryComponentDependencies
import ru.devkit.investapplication.MainActivity
import ru.devkit.service.di.ServiceModule
import javax.inject.Singleton

/**
 * @author k.i.tayupov
 */
@Component(
    modules = [
        AppModule::class,
        ServiceModule::class,
        RepositoryModule::class,
    ]
)
@Singleton
interface AppComponent : PortfolioComponentDependencies,
    StockHistoryComponentDependencies,
    SummaryComponentDependencies {

    override fun portfolioRepository(): PortfolioRepository

    fun inject(activity: MainActivity)
}
