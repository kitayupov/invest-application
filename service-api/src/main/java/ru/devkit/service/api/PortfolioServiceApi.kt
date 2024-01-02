package ru.devkit.service.api

import kotlinx.coroutines.flow.Flow
import ru.devkit.service.api.data.Portfolio

interface PortfolioServiceApi {
    fun getPortfolio(): Flow<Portfolio>
    fun release()
}
