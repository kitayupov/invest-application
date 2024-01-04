package ru.devkit.service.base

import okhttp3.Interceptor
import okhttp3.Response
import ru.devkit.service.crypt.Crypt

/**
 * @author k.i.tayupov
 */
internal class AuthInterceptor(token: String) : Interceptor {

    private val authHeaderValue = "Bearer ${Crypt.decrypt(token)}"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", authHeaderValue)
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}
