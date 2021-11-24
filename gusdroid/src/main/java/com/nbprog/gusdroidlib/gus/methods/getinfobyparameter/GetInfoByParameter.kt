package com.nbprog.gusdroidlib.gus.methods.getinfobyparameter

import com.nbprog.gusdroidlib.gus.Configuration
import com.nbprog.gusdroidlib.gus.Gus
import com.nbprog.gusdroidlib.gus.base.BaseRequest
import com.nbprog.gusdroidlib.gus.base.DomainMapper
import com.nbprog.gusdroidlib.gus.base.RequestBuilder
import com.nbprog.gusdroidlib.model.*
import com.nbprog.gusdroidlib.util.readAsHTML

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