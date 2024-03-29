package ru.devkit.service.mock

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.devkit.service.PortfolioServiceApi
import ru.devkit.service.data.InvestmentApi
import ru.devkit.service.data.PortfolioApi
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author k.i.tayupov
 */
@Singleton
class MockPortfolioService @Inject constructor(
    private val commonService: MockCommonService,
) : PortfolioServiceApi {

    private var data = AtomicReference(PortfolioApi(emptyList()))

    private var isRunning = false

    override fun attach() {
        isRunning = true
        data.set(
            PortfolioApi(
                items = commonService.dto.map {
                    InvestmentApi(
                        symbol = it.symbol,
                        quantity = it.quantity,
                        initialPrice = it.price
                    )
                }
            )
        )
    }

    override fun release() {
        isRunning = false
    }

    override fun getPortfolio(): Flow<PortfolioApi> = flow {
        while (isRunning) {
            emit(data.get())
            delay(1_000)
        }
    }
}
