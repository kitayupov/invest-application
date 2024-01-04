package ru.devkit.service.api.portfolio.data.request

import ru.devkit.service.api.portfolio.data.MoneyValue

/**
 * @author k.i.tayupov
 */
internal data class SandboxPayInRequest(
    val accountId: String,
    val amount: MoneyValue
)
