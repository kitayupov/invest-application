package ru.devkit.service

import kotlinx.coroutines.flow.Flow
import ru.devkit.service.data.PortfolioApi

/**
 * @author k.i.tayupov
 */
interface PortfolioServiceApi {

    fun getPortfolio(): Flow<PortfolioApi>
}
