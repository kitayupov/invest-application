package ru.devkit.repository.api

import kotlinx.coroutines.flow.Flow
import ru.devkit.repository.api.data.Portfolio

interface PortfolioApi {
    fun getPortfolio(): Flow<Portfolio>
    fun release()
}
