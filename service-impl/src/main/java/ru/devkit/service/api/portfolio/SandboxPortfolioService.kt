package ru.devkit.service.api.portfolio

import retrofit2.http.Body
import retrofit2.http.POST
import ru.devkit.service.api.portfolio.data.request.AccountRequest
import ru.devkit.service.api.portfolio.data.response.GetSandboxAccountsResponse
import ru.devkit.service.api.portfolio.data.response.GetSandboxPortfolioResponse
import ru.devkit.service.api.portfolio.data.request.GetSandboxPortfolioRequest
import ru.devkit.service.api.portfolio.data.request.SandboxPayInRequest
import ru.devkit.service.api.portfolio.data.response.SandboxPayInResponse
import ru.devkit.service.api.portfolio.data.request.PostSandboxOrderRequest
import ru.devkit.service.api.portfolio.data.response.GetSandboxOrdersResponse
import ru.devkit.service.api.portfolio.data.response.PostSandboxOrderResponse

/**
 * @author k.i.tayupov
 *
 * Open Api https://tinkoff.github.io/investAPI/swagger-ui
 *
 */
internal interface SandboxPortfolioService {

    @POST("tinkoff.public.invest.api.contract.v1.SandboxService/GetSandboxAccounts")
    suspend fun getAccounts(@Body data: Any = Any()): GetSandboxAccountsResponse

    @POST("tinkoff.public.invest.api.contract.v1.SandboxService/GetSandboxPortfolio")
    suspend fun getSandboxPortfolio(@Body data: GetSandboxPortfolioRequest): GetSandboxPortfolioResponse

    @POST("tinkoff.public.invest.api.contract.v1.SandboxService/SandboxPayIn")
    suspend fun sandboxPayIn(@Body data: SandboxPayInRequest): SandboxPayInResponse

    @POST("tinkoff.public.invest.api.contract.v1.SandboxService/PostSandboxOrder")
    suspend fun postSandboxOrder(@Body data: PostSandboxOrderRequest): PostSandboxOrderResponse

    @POST("tinkoff.public.invest.api.contract.v1.SandboxService/GetSandboxOrders")
    suspend fun getSandboxOrders(@Body data: AccountRequest): GetSandboxOrdersResponse
}
