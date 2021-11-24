package com.nbprog.gusdroidlib.gus.methods.authenticate

import com.nbprog.gusdroidlib.gus.base.DomainMapper
import com.nbprog.gusdroidlib.model.AuthResult
import com.nbprog.gusdroidlib.model.XMLParseException
import com.nbprog.gusdroidlib.util.extractFromTag

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

