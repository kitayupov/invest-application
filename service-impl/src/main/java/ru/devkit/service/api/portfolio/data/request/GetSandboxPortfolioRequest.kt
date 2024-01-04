package ru.devkit.service.api.portfolio.data.request

/**
 * @author k.i.tayupov
 */
internal data class GetSandboxPortfolioRequest(
    val accountId: String,
    val currency: String = "RUB"
)
