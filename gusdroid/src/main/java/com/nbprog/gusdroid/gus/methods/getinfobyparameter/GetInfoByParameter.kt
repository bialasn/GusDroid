package com.nbprog.gusdroid.gus.methods.getinfobyparameter

import com.nbprog.gusdroid.gus.Configuration
import com.nbprog.gusdroid.gus.Gus
import com.nbprog.gusdroid.gus.base.BaseRequest
import com.nbprog.gusdroid.gus.base.DomainMapper
import com.nbprog.gusdroid.gus.base.RequestBuilder
import com.nbprog.gusdroid.model.*
import com.nbprog.gusdroid.util.readAsHTML

class GetInfoByParameter : BaseRequest{

    override val requestBuilder: RequestBuilder
        get() = GetInfoByParameterRequestBuilder()
    override val mapper: DomainMapper<String, FullReport>
        get() = GetInfoByParameterMapper()

    override suspend fun proceed(params: Map<String, String?>): GusResult<FullReport> {
        val request = requestBuilder.createRequest(params)

        return safeApiCall {
            val session = Gus.sessionIdentifier
            val requestResult = Configuration.gusService.getFullReportS(session, request).readAsHTML()
            mapper.map(requestResult)
        }
    }

}