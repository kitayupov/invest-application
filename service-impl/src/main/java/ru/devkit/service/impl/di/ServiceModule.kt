package ru.devkit.service.impl.di

import dagger.Binds
import dagger.Module
import ru.devkit.service.mock.impl.MockPortfolioService
import ru.devkit.service.mock.impl.MockStocksService
import ru.devkit.service.api.PortfolioServiceApi
import ru.devkit.service.api.StocksServiceApi

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
