package com.nbprog.gusdroid.gus.methods.getinfobyparameter

import com.nbprog.gusdroid.gus.base.DomainMapper
import com.nbprog.gusdroid.model.FullReport
import com.nbprog.gusdroid.model.XMLParseException
import com.nbprog.gusdroid.util.extractFromTag

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

