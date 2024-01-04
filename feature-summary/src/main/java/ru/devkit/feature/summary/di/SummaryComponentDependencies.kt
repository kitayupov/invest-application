package ru.devkit.feature.summary.di

import ru.devkit.common.di.ComponentDependencies
import ru.devkit.domain.repository.PortfolioRepository

/**
 * @author k.i.tayupov
 */
interface SummaryComponentDependencies : ComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}
