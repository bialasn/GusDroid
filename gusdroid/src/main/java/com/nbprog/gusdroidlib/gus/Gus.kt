package com.nbprog.gusdroidlib.gus

import com.nbprog.gusdroidlib.gus.methods.authenticate.Authenticate
import com.nbprog.gusdroidlib.gus.methods.authenticate.AuthenticateRequestBuilder
import com.nbprog.gusdroidlib.gus.methods.getinfobyparameter.GetInfoByParameter
import com.nbprog.gusdroidlib.gus.methods.getinfobyparameter.GetInfoByParameterRequestBuilder
import com.nbprog.gusdroidlib.model.AuthResult
import com.nbprog.gusdroidlib.model.FullReport
import com.nbprog.gusdroidlib.model.GusResult


object Gus {
    var sessionIdentifier : String? = null

    suspend fun getInfoBy(nip : String? = null, regon : String? = null) : GusResult<FullReport> {
        val params = mapOf(
            GetInfoByParameterRequestBuilder.NIP to nip,
            GetInfoByParameterRequestBuilder.REGON to regon
        )
        return GetInfoByParameter().proceed(params)
    }

    suspend fun authenticate(authKey : String) : GusResult<AuthResult> {
        val params = mapOf(
            AuthenticateRequestBuilder.KEY to authKey
        )
        val result = Authenticate().proceed(params)
        if(result.isSuccess()){
            sessionIdentifier = (result as? GusResult.Success)?.value?.authKey
        }
        return Authenticate().proceed(params)
    }
}