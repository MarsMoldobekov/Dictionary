package com.example.dictionary.frameworks.web

import com.example.dictionary.entities.Word
import com.example.dictionary.frameworks.datasource.DataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebDataSource : DataSource<List<Word>> {
    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }

    private val api: ApiService = Retrofit.Builder().baseUrl(BASE_URL_LOCATIONS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient(BaseInterceptor.interceptor))
        .build().create(ApiService::class.java)

    override suspend fun getData(word: String): List<Word> {
        return api.searchAsync(word)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build()
    }

    class BaseInterceptor private constructor() : Interceptor {
        companion object {
            val interceptor: BaseInterceptor
                get() = BaseInterceptor()
        }

        enum class ServerResponseStatusCode {
            INFO,
            SUCCESS,
            REDIRECTION,
            CLIENT_ERROR,
            SERVER_ERROR,
            UNDEFINED_ERROR
        }

        private var responseCode: Int = 0

        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request()).also { responseCode = it.code }
        }

        fun getResponseCode(): ServerResponseStatusCode {
            return when (responseCode / 100) {
                1 -> ServerResponseStatusCode.INFO
                2 -> ServerResponseStatusCode.SUCCESS
                3 -> ServerResponseStatusCode.REDIRECTION
                4 -> ServerResponseStatusCode.CLIENT_ERROR
                5 -> ServerResponseStatusCode.SERVER_ERROR
                else -> ServerResponseStatusCode.UNDEFINED_ERROR
            }
        }
    }
}