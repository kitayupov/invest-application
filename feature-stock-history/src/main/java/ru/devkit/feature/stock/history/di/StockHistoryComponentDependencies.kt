package ru.devkit.feature.stock.history.di

import ru.devkit.common.di.ComponentDependencies
import ru.devkit.domain.repository.PortfolioRepository

/**
 * @author k.i.tayupov
 */
interface StockHistoryComponentDependencies : ComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}
