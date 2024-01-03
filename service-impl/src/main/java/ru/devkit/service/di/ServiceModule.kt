package ru.devkit.service.di

import dagger.Binds
import dagger.Module
import ru.devkit.service.mock.MockPortfolioService
import ru.devkit.service.mock.MockStocksService
import ru.devkit.service.PortfolioServiceApi
import ru.devkit.service.StocksServiceApi

/**
 * @author k.i.tayupov
 */
@Module
interface ServiceModule {

    @Binds
    fun portfolioService(impl: MockPortfolioService): PortfolioServiceApi

    @Binds
    fun stockService(impl: MockStocksService): StocksServiceApi
}
