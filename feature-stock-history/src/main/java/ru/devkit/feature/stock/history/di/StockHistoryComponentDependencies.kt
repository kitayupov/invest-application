package ru.devkit.feature.stock.history.di

import ru.devkit.domain.repository.PortfolioRepository

interface StockHistoryComponentDependencies {

    fun portfolioRepository(): PortfolioRepository
}