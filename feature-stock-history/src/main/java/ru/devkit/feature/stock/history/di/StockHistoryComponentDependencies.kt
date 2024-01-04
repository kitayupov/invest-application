package ru.devkit.feature.stock.history.di

import ru.devkit.domain.repository.PortfolioRepository

/**
 * @author k.i.tayupov
 */
interface StockHistoryComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}