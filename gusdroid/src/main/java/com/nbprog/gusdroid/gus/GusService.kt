package com.nbprog.gusdroid.gus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface GusService {
    @Headers("Content-Type: application/soap+xml")
    @POST("UslugaBIRzewnPubl.svc")
    suspend fun loginS(@Body body : String): String

    @Headers("Content-Type: application/soap+xml")
    @POST("UslugaBIRzewnPubl.svc")
    suspend fun getFullReportS(@Header("sid") sid : String?, @Body body : String): String
}