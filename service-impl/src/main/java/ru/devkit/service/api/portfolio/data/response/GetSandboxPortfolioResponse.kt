package ru.devkit.service.api.portfolio.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.devkit.service.api.portfolio.data.MoneyValue

/**
 * @author k.i.tayupov
 */
@Serializable
internal data class GetSandboxPortfolioResponse(

    @SerialName("accountId")
    val accountId: String,

    @SerialName("totalAmountShares")
    val totalAmountShares: MoneyValue,

    @SerialName("totalAmountBonds")
    val totalAmountBonds: MoneyValue,

    @SerialName("totalAmountEtf")
    val totalAmountEtf: MoneyValue,

    @SerialName("totalAmountCurrencies")
    val totalAmountCurrencies: MoneyValue,

    @SerialName("totalAmountFutures")
    val totalAmountFutures: MoneyValue,

    @SerialName("totalAmountOptions")
    val totalAmountOptions: MoneyValue,

    @SerialName("totalAmountSp")
    val totalAmountSp: MoneyValue,

    @SerialName("totalAmountPortfolio")
    val totalAmountPortfolio: MoneyValue,
)
