package ru.devkit.feature.stock.history.di

import ru.devkit.feature.stock.history.StockHistoryFragment

/**
 * @author k.i.tayupov
 */
interface StockHistoryComponent {

    fun inject(fragment: StockHistoryFragment)
}
