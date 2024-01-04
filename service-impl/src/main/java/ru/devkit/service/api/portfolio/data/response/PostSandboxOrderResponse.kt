package ru.devkit.service.api.portfolio.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author k.i.tayupov
 */
@Serializable
data class PostSandboxOrderResponse(

    @SerialName("orderId")
    val orderId: String,
)
