package ru.devkit.feature.stock.history.di

import dagger.Component
import ru.devkit.feature.stock.history.StockHistoryFragment

/**
 * @author k.i.tayupov
 */
@StockHistoryScope
@Component(dependencies = [StockHistoryComponentDependencies::class])
internal interface StockHistoryComponent {

    fun inject(fragment: StockHistoryFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: StockHistoryComponentDependencies): StockHistoryComponent
    }
}
