package com.cravefood.androidappmodel.data.repository.repository_util

import com.cravefood.androidappmodel.util.NetworkUtils
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitBuilder(
    private val cacheDir: File?,
    private val networkUtils: NetworkUtils
) {

    private var isUserLogged: Boolean? = false
    private var retrofit: Retrofit? = null
    private var cacheSize = 10 * 1024 * 1024 // 10 MB

    private val gsonConverter: GsonConverterFactory
        get() {
            val gson = GsonBuilder()
                .addSerializationExclusionStrategy(AnnotationSerializationStrategy())
                .addDeserializationExclusionStrategy(AnnotationDeserializationStrategy())
                .create()

            return GsonConverterFactory.create(gson)
        }

    private val httpClient: OkHttpClient
        get() {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)

            cacheDir?.let {
                val httpCacheDirectory = File(cacheDir, "http-cache")
                val cache = Cache(httpCacheDirectory, cacheSize.toLong())
                builder.cache(cache)
            }

            builder.addInterceptor { chain ->
                if (!networkUtils.isOnline()) {
                    throw NoInternetConnectionException("")
                }
                chain.proceed(chain.request())
            }

            return builder.build()
        }

    fun <T> build(service: Class<T>): T {
        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .addConverterFactory(gsonConverter)
                .baseUrl(networkUtils.getApiUrl())
                .client(httpClient)
                .build()
        }

        return this.retrofit!!.create(service)
    }
}