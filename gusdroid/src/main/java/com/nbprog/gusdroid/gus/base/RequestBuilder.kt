package com.nbprog.gusdroid.gus.base

interface RequestBuilder {
    val request : String

    fun createRequest(parameters: Map<String, String?>) : String
}