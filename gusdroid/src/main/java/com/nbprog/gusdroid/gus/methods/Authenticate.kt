package com.nbprog.gusdroid.gus.methods

import com.nbprog.gusdroid.gus.Gus
import com.nbprog.gusdroid.model.*
import com.nbprog.gusdroid.util.extractFromTag
import com.nbprog.gusdroid.util.readAsHTML

class Authenticate {

    val body = """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
                xmlns:ns="http://CIS/BIR/PUBL/2014/07">
                <soap:Header xmlns:wsa="http://www.w3.org/2005/08/addressing">
                <wsa:To>https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc</wsa:To>
                <wsa:Action>http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj</wsa:Action>
                </soap:Header>
                <soap:Body>
                <ns:Zaloguj>
                <ns:pKluczUzytkownika></ns:pKluczUzytkownika>
                </ns:Zaloguj>
                </soap:Body>
                </soap:Envelope>"""

    //TODO: adding parameters like map e.x "TAG_NAME" to "PARAMETER", request builder can have overriding body parameter or ext function on string, with map of parameters
    private fun requestBuilder(authKey: String? = null) : String {
        var request = body

        if(authKey != null){
            request = body.replace(" <ns:pKluczUzytkownika></ns:pKluczUzytkownika>"," <ns:pKluczUzytkownika>$authKey</ns:pKluczUzytkownika>")
        }
        return request
    }

    //TODO: move to other class as interface mapper and pass to this class?, map of tags??
    fun String.mapToAuthResult() : AuthResult {
        return try {
            val authKey = extractFromTag("ZalogujResult")
            AuthResult(authKey)
        }catch (e : Exception) {
            throw XMLParseException(e.message.orEmpty())
        }
    }

    suspend fun authenticate(authKey: String? = null): GusResult<AuthResult> {
        val request = requestBuilder(authKey)

        return safeApiCall {
            //TODO: get this from configuration
            Gus.gusService.loginS(request)
                .mapToAuthResult()
        }
    }
}