package ru.devkit.service.mock.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.devkit.service.api.PortfolioServiceApi
import ru.devkit.service.api.data.InvestmentApi
import ru.devkit.service.api.data.PortfolioApi
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

/**
 * @author k.i.tayupov
 */
class MockPortfolioService @Inject constructor(
    private val commonService: MockCommonService,
) : PortfolioServiceApi {

    private var data = AtomicReference(PortfolioApi(emptyList()))

    private var isRunning = true

    init {
        data.set(
            PortfolioApi(
                items = commonService.dto.map {
                    InvestmentApi(
                        symbol = it.id,
                        quantity = it.quantity,
                        initialPrice = it.price
                    )
                }
            )
        )
    }

    fun release() {
        isRunning = false
    }

    override fun getPortfolio(): Flow<PortfolioApi> = flow {
        while (isRunning) {
            emit(data.get())
            delay(1_000)
        }
    }
}
