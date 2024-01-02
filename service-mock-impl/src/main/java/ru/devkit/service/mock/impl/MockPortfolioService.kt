package ru.devkit.service.mock.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.devkit.service.api.PortfolioServiceApi
import ru.devkit.service.api.data.Investment
import ru.devkit.service.api.data.Portfolio
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

class MockPortfolioService @Inject constructor(
    private val commonService: MockCommonService
) : PortfolioServiceApi {

    private var data = AtomicReference(Portfolio(emptyList()))

    private var isRunning = true

    init {
        data.set(
            Portfolio(
                items = commonService.dto.map {
                    Investment(
                        id = it.id,
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

    override fun getPortfolio(): Flow<Portfolio> = flow {
        while (isRunning) {
            emit(data.get())
            delay(1_000)
        }
    }
}
