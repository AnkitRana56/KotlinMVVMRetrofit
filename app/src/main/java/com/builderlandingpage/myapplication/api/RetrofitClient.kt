package com.builderlandingpage.myapplication.api

import com.builderlandingpage.myapplication.utility.VariableBag
import com.google.gson.GsonBuilder
import com.squareup.okhttp.Credentials.basic
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object RetrofitClient {

    val retrofitClient: Retrofit.Builder by lazy {

        val contentType = "application/json".toMediaType()

        val key: String = "bmsapikey"

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val credentials: String = basic(key, key)
            val request = original.newBuilder()
                .header("key", key)
                .header("Authorization", credentials)
                .header("Accept", "application/json")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })

        val okHttpClient = httpClient.connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .readTimeout(190, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor).build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ): Converter<ResponseBody, *>? {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
                return Converter<ResponseBody, Any> {
                    if (it.contentLength() != 0L) {
                        nextResponseBodyConverter.convert(it)
                    } else {
                        null
                    }
                }
            }
        }

        Retrofit.Builder()
            .baseUrl(VariableBag.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    }
    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }

}