package ru.devkit.repository.impl.di

import dagger.Binds
import dagger.Module
import ru.devkit.repository.api.PortfolioApi
import ru.devkit.repository.api.StocksApi
import ru.devkit.repository.mock.impl.MockPortfolioService
import ru.devkit.repository.mock.impl.MockStocksService

/**
 * @author k.i.tayupov
 */
@Module
interface RepositoryModule {

    @Binds
    fun portfolioRepository(impl: MockPortfolioService): PortfolioApi

    @Binds
    fun stockRepository(impl: MockStocksService): StocksApi
}
