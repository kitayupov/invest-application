package ru.devkit.service.api.portfolio.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author k.i.tayupov
 */
@Serializable
internal data class MoneyValue(

    @SerialName("currency")
    val currency: String,

    @SerialName("units")
    val units: String,

    @SerialName("nano")
    val nano: Int,
)
