package com.nbprog.gusdroid.gus.base

import com.nbprog.gusdroid.model.GusResult

interface BaseRequest {

    val requestBuilder : RequestBuilder
    val mapper : DomainMapper<*,*>

    suspend fun proceed(params : Map<String,String>) : GusResult<*>
}