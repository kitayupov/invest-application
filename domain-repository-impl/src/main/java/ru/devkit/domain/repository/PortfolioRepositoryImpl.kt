package ru.devkit.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.devkit.domain.repository.data.Investment
import ru.devkit.domain.repository.data.Portfolio
import ru.devkit.domain.repository.mapper.InvestmentApiToDomainMapper
import ru.devkit.service.api.PortfolioServiceApi
import ru.devkit.service.api.StocksServiceApi
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val portfolioService: PortfolioServiceApi,
    private val stocksService: StocksServiceApi,
    private val mapper: InvestmentApiToDomainMapper,
) : PortfolioRepository {

    override fun getPortfolio(): Flow<Portfolio> {
        return portfolioService.getPortfolio()
            .map { data ->
                val investments = data.items.map { mapper(it, stocksService.getStock(it.id)) }
                Portfolio(investments)
            }
    }

    override fun getInvestment(symbol: String): Flow<Investment> {
        return getPortfolio().map { portfolio ->
            portfolio.investments.find { investment -> investment.id == symbol }
                ?: throw IllegalArgumentException("Investment with symbol: \'$symbol\' was not found in portfolio")
        }
    }

    override fun getStockHistory(symbol: String): List<Double> {
        return stocksService.getStockHistory(symbol)
    }
}
