package ru.devkit.service.api.portfolio.data.request

/**
 * @author k.i.tayupov
 */
internal data class PostSandboxOrderRequest(
    val figi: String,
    val quantity: String,
    val price: Price,
    val accountId: String,
    val direction: String = "ORDER_DIRECTION_BUY",
    val orderType: String = "ORDER_TYPE_BESTPRICE"
)

internal data class Price(
    val units: String,
    val nano: Int = 0,
)
