package ru.devkit.feature.summary.di

import ru.devkit.domain.repository.PortfolioRepository

interface SummaryComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}