package ru.devkit.service.api.portfolio.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author k.i.tayupov
 */
@Serializable
internal data class GetSandboxAccountsResponse(
    @SerialName("accounts")
    val accounts: List<SandboxAccount>
)

@Serializable
internal data class SandboxAccount(

    @SerialName("id")
    val id: String,

    @SerialName("type")
    val type: String,

    @SerialName("name")
    val name: String,

    @SerialName("status")
    val status: String,

    @SerialName("openedDate")
    val openedDate: String,

    @SerialName("accessLevel")
    val accessLevel: String,
)
