package ru.devkit.service.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.devkit.service.PortfolioServiceApi
import ru.devkit.service.api.portfolio.SandboxPortfolioService
import ru.devkit.service.api.portfolio.data.request.AccountRequest
import ru.devkit.service.base.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author k.i.tayupov
 */
@Singleton
class SandboxPortfolioService @Inject constructor() : PortfolioServiceApi {

    private val api = NetworkService.create(SandboxPortfolioService::class.java)

    private var isStarted = true

    override fun getPortfolio(): Flow<PortfolioApi> {
        return flow {
            val accountId = api.getAccounts().accounts[0].id
            val orders = api.getSandboxOrders(AccountRequest(accountId))

            val investments = orders.orders.map { order ->
                InvestmentApi(
                    symbol = order.figi,
                    quantity = order.lotsRequested.toInt(),
                    initialPrice = order.averagePositionPrice.units.toDouble()
                )
            }

            while (isStarted) {
                emit(PortfolioApi(investments))
                delay(1_000)
            }
        }
    }

    fun start() {
        isStarted = true
    }

    fun release() {
        isStarted = false
    }
}
