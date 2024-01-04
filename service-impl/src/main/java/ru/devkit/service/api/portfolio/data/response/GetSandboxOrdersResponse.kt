package ru.devkit.service.api.portfolio.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.devkit.service.api.portfolio.data.MoneyValue

/**
 * @author k.i.tayupov
 */
@Serializable
internal data class GetSandboxOrdersResponse(

    @SerialName("orders")
    val orders: List<Order>
)

@Serializable
internal data class Order(

    @SerialName("orderId")
    val orderId: String,

    @SerialName("figi")
    val figi: String,

    @SerialName("lotsRequested")
    val lotsRequested: String,

    @SerialName("averagePositionPrice")
    val averagePositionPrice: MoneyValue
)
