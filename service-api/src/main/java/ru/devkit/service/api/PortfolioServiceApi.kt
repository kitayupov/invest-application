package ru.devkit.service.api

import kotlinx.coroutines.flow.Flow
import ru.devkit.service.api.data.PortfolioApi

/**
 * @author k.i.tayupov
 */
interface PortfolioServiceApi {

    fun getPortfolio(): Flow<PortfolioApi>
}
