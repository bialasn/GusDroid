package com.nbprog.gusdroidlib.gus.methods.getinfobyparameter

import com.nbprog.gusdroidlib.gus.base.DomainMapper
import com.nbprog.gusdroidlib.model.FullReport
import com.nbprog.gusdroidlib.model.XMLParseException
import com.nbprog.gusdroidlib.util.extractFromTag

class GetInfoByParameterMapper : DomainMapper<String, FullReport> {
    override fun map(objectToMap: String): FullReport {
        return try {
            val fullReport = objectToMap.run {
                FullReport(
                    name = extractFromTag("Nazwa"),
                    community = extractFromTag("Gmina"),
                    voivodeship = extractFromTag("Wojewodztwo"),
                    regon = extractFromTag("Regon"),
                    statusNip = extractFromTag("StatusNip"),
                    district = extractFromTag("Powiat"),
                    postalCode = extractFromTag("KodPocztowy"),
                    streetName = extractFromTag("Ulica"),
                    propertyNumber = extractFromTag("NrNieruchomosci"),
                    localNumber = extractFromTag("NrLokalu"),
                    type = extractFromTag("Typ"),
                    silosId = extractFromTag("SilosID"),
                    businessTerminationDate = extractFromTag("DataZakonczeniaDzialalnosci"),
                    locationOfPost = extractFromTag("MiejscowoscPoczty"),
                    location = extractFromTag("Miejscowosc")
                )
            }
            fullReport
        }catch (e : Exception) {
            throw XMLParseException(e.message.orEmpty())
        }
    }
}

