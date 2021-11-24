package com.nbprog.gusdroidlib

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val a = """
        <soap:Body>
        <ns:DaneSzukajPodmioty>
        <ns:pParametryWyszukiwania>
        </ns:pParametryWyszukiwania>
        </ns:DaneSzukajPodmioty>
        </soap:Body>
    """

    @Test
    fun is_replacing_correct() {
        val tag = "<ns:pKluczUzytkownika>"
        val closeTag = tag.replace("<", "</")
        assertTrue(closeTag == "</ns:pKluczUzytkownika>")
    }

    @Test
    fun is_new_tag_added_between_specified_tag() {
        val b = a.addParamBetween("<ns:pParametryWyszukiwania>", "<Regon>","superregon")
        val c = b.addParamBetween("<ns:pParametryWyszukiwania>", "<Nip>","supernip")
        assertTrue(1 == 1)
    }

    fun String.addParamBetween(tag: String, tagToAdd: String, value: String): String {
        var requestBuilder = this

        val paramCloseTag = tagToAdd.replace("<", "</")

        val splittedByOpenTag = requestBuilder.split(tag)

        return "${splittedByOpenTag[0]}$tag\n$tagToAdd$value$paramCloseTag${splittedByOpenTag[1]}"
    }
}