package ru.devkit.service.base

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://sandbox-invest-public-api.tinkoff.ru/rest/"
private const val CRYPT_TOKEN = "ByEtsl8Zwin8FP/WzVqmBa7W1Hfl9g0Di/fUJXjsPbW/PohKcpxibXA6UJWVVHv9IcDbStSleD6yPUZ50qYvCZfwCEDK4EluQq8pwf1g7tQJPOBlvz8huD64lFU868vr"

internal object NetworkService {

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(CRYPT_TOKEN))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    fun <T : Any> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
