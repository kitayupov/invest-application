package ru.devkit.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.devkit.domain.repository.data.Investment
import ru.devkit.domain.repository.data.Portfolio
import ru.devkit.service.api.PortfolioServiceApi
import ru.devkit.service.api.StocksServiceApi
import ru.devkit.service.api.data.InvestmentApi
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val portfolioService: PortfolioServiceApi,
    private val stocksService: StocksServiceApi,
) : PortfolioRepository {
    val data = portfolioService.getPortfolio()
        .map { data ->
            val investments = data.items.map { it.toInvestment() }
            val portfolio = Portfolio(investments)
            portfolio
        }

    override fun getPortfolio(): Flow<Portfolio> {
        return portfolioService.getPortfolio()
            .map { data ->
                val investments = data.items.map { it.toInvestment() }
                val portfolio = Portfolio(investments)
                portfolio
            }
    }

    override fun getStockHistory(symbol: String): List<Double> {
        return stocksService.getStockHistory(symbol)
    }

    private fun InvestmentApi.toInvestment(): Investment {
        val stock = stocksService.getStock(id)
        return Investment(
            id = id,
            name = stock.name,
            quantity = quantity,
            currentPrice = stock.currentPrice,
            firstPrice = initialPrice,
            lastPrice = stock.lastPrice
        )
    }
}
