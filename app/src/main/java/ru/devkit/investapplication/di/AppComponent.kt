package ru.devkit.investapplication.di

import dagger.Component
import ru.devkit.domain.repository.di.RepositoryModule
import ru.devkit.feature.portfolio.PortfolioFragment
import ru.devkit.feature.stock.history.StockHistoryFragment
import ru.devkit.feature.summary.SummaryFragment
import ru.devkit.investapplication.MainActivity
import ru.devkit.service.impl.di.ServiceModule
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
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: PortfolioFragment)

    fun inject(fragment: SummaryFragment)

    fun inject(fragment: StockHistoryFragment)
}
