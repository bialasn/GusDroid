package com.nbprog.gusdroid.gus.methods.authenticate

import com.nbprog.gusdroid.gus.base.DomainMapper
import com.nbprog.gusdroid.model.AuthResult
import com.nbprog.gusdroid.model.XMLParseException
import com.nbprog.gusdroid.util.extractFromTag

class AuthenticateMapper : DomainMapper<String, AuthResult> {
    override fun map(objectToMap: String): AuthResult {
        return try {
            val authKey = objectToMap.extractFromTag("ZalogujResult")
            AuthResult(authKey)
        }catch (e : Exception) {
            throw XMLParseException(e.message.orEmpty())
        }
    }
}

