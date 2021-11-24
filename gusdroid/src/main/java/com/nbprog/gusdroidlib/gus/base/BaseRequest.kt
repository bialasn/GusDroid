package com.nbprog.gusdroidlib.gus.base

import com.nbprog.gusdroidlib.model.GusResult

interface BaseRequest {

    val requestBuilder : RequestBuilder
    val mapper : DomainMapper<*,*>

    suspend fun proceed(params : Map<String,String?>) : GusResult<*>
}