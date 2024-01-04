package ru.devkit.service.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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
class SandboxPortfolioService @Inject constructor() : PortfolioServiceApi, CoroutineScope by MainScope() {

    private val api = NetworkService.create(SandboxPortfolioService::class.java)

    private var portfolio = PortfolioApi(emptyList())

    private var job: Job = Job()

    override fun getPortfolio(): Flow<PortfolioApi> {
        job.cancel()
        val flow = flow {
            while (true) {
                emit(portfolio)
                delay(1_000)
            }
        }
        job = launch { flow.cancellable() }
        return flow
    }

    override fun attach() {
        launch {
            val accountId = api.getAccounts().accounts[0].id
            val orders = api.getSandboxOrders(AccountRequest(accountId))

            val investments = orders.orders.distinctBy { it.figi }.map { order ->
                InvestmentApi(
                    symbol = order.figi,
                    quantity = order.lotsRequested.toInt(),
                    initialPrice = order.averagePositionPrice.units.toDouble()
                )
            }

            portfolio = PortfolioApi(investments)
        }
    }

    override fun release() {
        job.cancel()
    }
}
