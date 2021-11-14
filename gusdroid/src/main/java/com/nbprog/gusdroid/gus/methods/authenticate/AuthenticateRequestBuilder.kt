package com.nbprog.gusdroid.gus.methods.authenticate

import com.nbprog.gusdroid.gus.base.RequestBuilder

class AuthenticateRequestBuilder : RequestBuilder {

    companion object {
        const val KEY = "<ns:pKluczUzytkownika>"
    }

    override val request: String
        get() = """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
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

    override fun createRequest(parameters: Map<String, String?>) : String{
        var requestCreator = request

        parameters.forEach { (paramName,paramValue) ->
            paramValue?.let {
                val paramCloseTag = paramName.replace("<","</")
                requestCreator = request.replace("$paramName$paramCloseTag","$paramName$it$paramCloseTag")
            }
        }
        return requestCreator
    }
}