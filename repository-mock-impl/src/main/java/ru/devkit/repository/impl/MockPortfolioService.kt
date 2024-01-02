package ru.devkit.repository.impl

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.devkit.repository.api.PortfolioApi
import ru.devkit.repository.api.data.Investment
import ru.devkit.repository.api.data.Portfolio
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

class MockPortfolioService @Inject constructor(
    private val commonService: MockCommonService
) : PortfolioApi {

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
