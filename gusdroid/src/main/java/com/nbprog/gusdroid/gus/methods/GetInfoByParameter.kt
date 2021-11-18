package com.nbprog.gusdroid.gus.methods

import com.nbprog.gusdroid.gus.Gus
import com.nbprog.gusdroid.model.FullReport
import com.nbprog.gusdroid.model.GusResult
import com.nbprog.gusdroid.model.XMLParseException
import com.nbprog.gusdroid.model.safeApiCall
import com.nbprog.gusdroid.util.extractFromTag
import com.nbprog.gusdroid.util.readAsHTML

class GetInfoByParameter {

    val body = """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
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

    //TODO: Move to other class?
    private fun requestBuilder(nip : String? = null, regon: String? = null) : String {
        var request = body

        if(nip != null){
            request = body.replace("<dat:Nip></dat:Nip>","<dat:Nip>$nip</dat:Nip>")
        }

        if(regon != null){
            request = body.replace("<dat:Regon></dat:Regon>","<dat:Regon>$regon</dat:Regon>")
        }
        return request
    }

    //TODO: move to other class as interface mapper and pass to this class?
    fun String.mapToFullReport() : FullReport {
        return try {
            val name = extractFromTag("Nazwa")
            val voivodeship = extractFromTag("Wojewodztwo")
            val community = extractFromTag("Gmina")
            FullReport(name, community, voivodeship)
        }catch (e : Exception) {
            throw XMLParseException(e.message.orEmpty())
        }
    }

    suspend fun getFullReport(nip : String? = null, regon : String? = null): GusResult<FullReport> {
        val request = requestBuilder(nip,regon)

        return safeApiCall {
            //TODO: get this from configuration
            Gus.gusService.getFullReportS(Gus.sessionIdentifier, request)
                .readAsHTML()
                .mapToFullReport()
        }
    }
}