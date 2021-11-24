package com.nbprog.gusdroidlib.model

import java.lang.Exception

data class XMLParseException(override val message : String) : Exception(message)
