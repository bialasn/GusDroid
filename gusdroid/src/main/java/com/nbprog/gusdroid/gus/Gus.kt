package com.nbprog.gusdroid.gus

import com.nbprog.gusdroid.gus.methods.authenticate.Authenticate
import com.nbprog.gusdroid.gus.methods.authenticate.AuthenticateRequestBuilder
import com.nbprog.gusdroid.gus.methods.getinfobyparameter.GetInfoByParameter
import com.nbprog.gusdroid.gus.methods.getinfobyparameter.GetInfoByParameterRequestBuilder
import com.nbprog.gusdroid.model.AuthResult
import com.nbprog.gusdroid.model.FullReport
import com.nbprog.gusdroid.model.GusResult


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