package ru.devkit.feature.portfolio.di

import ru.devkit.domain.repository.PortfolioRepository

/**
 * @author k.i.tayupov
 */
interface PortfolioComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}