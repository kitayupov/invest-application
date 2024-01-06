package ru.devkit.investapplication.di

import dagger.Module
import dagger.Provides
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import ru.devkit.domain.repository.PortfolioRepository
import ru.devkit.domain.repository.data.Portfolio
import ru.devkit.service.PortfolioServiceApi
import ru.devkit.service.StocksServiceApi

/**
 * @author k.i.tayupov
 */
@Module
class TestAppModule {

    @Provides
    fun mockPortfolioRepository(): PortfolioRepository = mockk {

        every { getPortfolio() } returns flow {
            Portfolio(emptyList())
        }
    }

    @Provides
    fun mockPortfolioServiceApi(): PortfolioServiceApi = mockk()

    @Provides
    fun mockStockServiceApi(): StocksServiceApi = mockk()
}
