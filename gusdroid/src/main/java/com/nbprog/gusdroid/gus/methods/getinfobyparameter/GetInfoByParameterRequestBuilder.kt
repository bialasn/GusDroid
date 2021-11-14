package com.nbprog.gusdroid.gus.methods.getinfobyparameter

import com.nbprog.gusdroid.gus.base.RequestBuilder

class GetInfoByParameterRequestBuilder : RequestBuilder {

    companion object {
        const val REGON = "<dat:Regon>"
        const val NIP = "<dat:Nip>"
    }

    override val request: String
        get() = """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
                xmlns:ns="http://CIS/BIR/PUBL/2014/07" xmlns:dat="http://CIS/BIR/PUBL/2014/07/DataContract">
                <soap:Header xmlns:wsa="http://www.w3.org/2005/08/addressing">
                <wsa:To>https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc</wsa:To>
                <wsa:Action>http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/DaneSzukajPodmioty</wsa:Action>
                </soap:Header>
                <soap:Body>
                <ns:DaneSzukajPodmioty>
                <ns:pParametryWyszukiwania>
                <!--Optional:-->
                <dat:Regon></dat:Regon>
                <!--Optional:-->
                <dat:Nip></dat:Nip>
                <!--Optional:-->
                <dat:Krs></dat:Krs>
                <!--Optional:-->
                <dat:Nipy></dat:Nipy>
                <!--Optional:-->
                <dat:Regony9zn></dat:Regony9zn>
                <!--Optional:-->
                <dat:Krsy></dat:Krsy>
                <!--Optional:-->
                <dat:Regony14zn></dat:Regony14zn>
                </ns:pParametryWyszukiwania>
                </ns:DaneSzukajPodmioty>
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