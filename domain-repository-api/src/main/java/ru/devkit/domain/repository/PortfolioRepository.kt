package ru.devkit.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.devkit.domain.repository.data.Investment
import ru.devkit.domain.repository.data.Portfolio

/**
 * @author k.i.tayupov
 */
interface PortfolioRepository {

    fun getPortfolio(): Flow<Portfolio>

    fun getInvestment(symbol: String): Flow<Investment>

    fun getStockHistory(symbol: String): List<Double>
}
