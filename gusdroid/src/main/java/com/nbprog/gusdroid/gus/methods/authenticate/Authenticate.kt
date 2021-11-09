package com.nbprog.gusdroid.gus.methods.authenticate

import com.nbprog.gusdroid.gus.Gus
import com.nbprog.gusdroid.gus.base.BaseRequest
import com.nbprog.gusdroid.gus.base.DomainMapper
import com.nbprog.gusdroid.gus.base.RequestBuilder
import com.nbprog.gusdroid.model.*
import com.nbprog.gusdroid.util.extractFromTag

class Authenticate : BaseRequest{

    override val requestBuilder: RequestBuilder
        get() = AuthenticateRequestBuilder()
    override val mapper: DomainMapper<String, AuthResult>
        get() = AuthenticateMapper()

    override suspend fun proceed(params: Map<String, String>): GusResult<AuthResult> {
        val request = requestBuilder.createRequest(params)

        return safeApiCall {
            val requestResult = Gus.gusService.loginS(request)
            mapper.map(requestResult)
        }
    }

}