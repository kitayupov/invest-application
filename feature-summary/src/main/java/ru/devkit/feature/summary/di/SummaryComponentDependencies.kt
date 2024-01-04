package ru.devkit.feature.summary.di

import ru.devkit.domain.repository.PortfolioRepository

/**
 * @author k.i.tayupov
 */
interface SummaryComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}