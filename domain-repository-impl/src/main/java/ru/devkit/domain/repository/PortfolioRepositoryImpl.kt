package ru.devkit.domain.repository

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.devkit.domain.repository.data.Investment
import ru.devkit.domain.repository.data.Portfolio
import ru.devkit.domain.repository.db.PortfolioDatabase
import ru.devkit.domain.repository.mapper.InvestmentApiToDbMapper
import ru.devkit.domain.repository.mapper.InvestmentDbToDomainMapper
import ru.devkit.service.PortfolioServiceApi
import ru.devkit.service.StocksServiceApi
import ru.devkit.service.data.PortfolioApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PortfolioRepositoryImpl @Inject constructor(
    private val context: Context,
    private val portfolioService: PortfolioServiceApi,
    private val stocksService: StocksServiceApi,
    private val apiToDbMapper: InvestmentApiToDbMapper,
    private val dbToDomainMapper: InvestmentDbToDomainMapper,
) : PortfolioRepository {

    private val db = Room.databaseBuilder(context, PortfolioDatabase::class.java, "portfolio-database").build()

    override fun getPortfolio(): Flow<Portfolio> {
        return portfolioService.getPortfolio()
            .dropWhile { it == PortfolioApi.EMPTY }
            .map { data ->
                val dbModels = data.items.map { apiToDbMapper(it, stocksService.getStock(it.symbol)) }
                db.portfolioDao().deleteAndInsert(dbModels)
                dbModels
            }
            .onStart { emit(db.portfolioDao().getAll()) }
            .map { data ->
                val domainModels = data.map { dbToDomainMapper(it) }
                Portfolio(domainModels)
            }
    }

    override fun getInvestment(symbol: String): Flow<Investment> {
        return getPortfolio().map { portfolio ->
            portfolio.investments.find { investment -> investment.symbol == symbol }
                ?: throw IllegalArgumentException("Investment with symbol: \'$symbol\' was not found in portfolio")
        }
    }

    override fun getStockHistory(symbol: String): List<Double> {
        return stocksService.getStockHistory(symbol)
    }
}
