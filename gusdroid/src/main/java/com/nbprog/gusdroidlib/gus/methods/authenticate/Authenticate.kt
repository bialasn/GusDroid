package com.nbprog.gusdroidlib.gus.methods.authenticate

import com.nbprog.gusdroidlib.gus.Configuration
import com.nbprog.gusdroidlib.gus.base.BaseRequest
import com.nbprog.gusdroidlib.gus.base.DomainMapper
import com.nbprog.gusdroidlib.gus.base.RequestBuilder
import com.nbprog.gusdroidlib.model.*

class Authenticate : BaseRequest{

    override val requestBuilder: RequestBuilder
        get() = AuthenticateRequestBuilder()
    override val mapper: DomainMapper<String, AuthResult>
        get() = AuthenticateMapper()

    override suspend fun proceed(params: Map<String, String?>): GusResult<AuthResult> {
        val request = requestBuilder.createRequest(params)

        return safeApiCall {
            val requestResult = Configuration.gusService.loginS(request)
            mapper.map(requestResult)
        }
    }

}