package ru.devkit.service.api.portfolio.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.devkit.service.api.portfolio.data.MoneyValue

/**
 * @author k.i.tayupov
 */
@Serializable
internal data class SandboxPayInResponse(

    @SerialName("balance")
    val balance: MoneyValue,
)
