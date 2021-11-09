package com.nbprog.gusdroid

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val tag = "<ns:pKluczUzytkownika>"
        val closeTag = tag.replace("<","</")
        assertTrue(closeTag == "</ns:pKluczUzytkownika>")
    }
}