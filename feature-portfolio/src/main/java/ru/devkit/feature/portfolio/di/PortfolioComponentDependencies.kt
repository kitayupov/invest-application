package ru.devkit.feature.portfolio.di

import ru.devkit.common.di.ComponentDependencies
import ru.devkit.domain.repository.PortfolioRepository

/**
 * @author k.i.tayupov
 */
interface PortfolioComponentDependencies : ComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}
