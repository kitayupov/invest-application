package ru.devkit.feature.portfolio.di

import ru.devkit.domain.repository.PortfolioRepository

interface PortfolioComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}