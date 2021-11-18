package com.nbprog.gusdroid.gus

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object Configuration {
    private const val BASE_URL = "https://wyszukiwarkaregon.stat.gov.pl/wsBIR/"

    private val xmlInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("Content-Type", "Content-Type: application/soap+xml");
        chain.proceed(requestBuilder.build());
    }

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(xmlInterceptor)
        .build();

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())

    private val retrofitInstance = retrofitBuilder
        .baseUrl(BASE_URL)
        .client(okHttpClient).build()

    val gusService = retrofitInstance.create(GusService::class.java)
}