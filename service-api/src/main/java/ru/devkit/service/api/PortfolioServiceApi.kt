package ru.devkit.service.api

import kotlinx.coroutines.flow.Flow
import ru.devkit.service.api.data.PortfolioApi

interface PortfolioServiceApi {
    fun getPortfolio(): Flow<PortfolioApi>
    fun release()
}
