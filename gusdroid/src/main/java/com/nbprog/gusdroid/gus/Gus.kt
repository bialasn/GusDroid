package com.nbprog.gusdroid.gus

import com.nbprog.gusdroid.gus.methods.Authenticate
import com.nbprog.gusdroid.gus.methods.GetInfoByParameter
import com.nbprog.gusdroid.model.AuthResult
import com.nbprog.gusdroid.model.FullReport
import com.nbprog.gusdroid.model.GusResult
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object Gus {

    //TODO: move to sth like configuration
    const val BASE_URL = "https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/"

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val xmlInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder();
        requestBuilder.header("Content-Type", "Content-Type: application/soap+xml");
        chain.proceed(requestBuilder.build());
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(xmlInterceptor)
        .build();


    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())

    val retrofitInstance = retrofitBuilder
        .baseUrl(BASE_URL)
        .client(okHttpClient).build()

    val gusService = retrofitInstance.create(GusService::class.java)

    var sessionIdentifier : String? = null
    // end of configuration

    //good, exposure method like this
    suspend fun getInfoBy(nip : String? = null, regon : String? = null) : GusResult<FullReport> {
        return GetInfoByParameter().getFullReport(nip, regon)
    }

    suspend fun authenticate(authKey : String) : GusResult<AuthResult> {
        return Authenticate().authenticate(authKey)
    }
}