package com.nbprog.gusdroidlib.util

import android.text.Html
import java.lang.Exception
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.extractFromTag(tagName : String): String? {
    return try {
        val p: Pattern = Pattern.compile("<$tagName>(.+?)</$tagName>", Pattern.DOTALL)
        val m: Matcher = p.matcher(this)
        m.find()
        m.group(1).orEmpty()
    }catch(e : Exception) {
        null
    }
}

fun String.readAsHTML(): String {
    return Html.fromHtml(this).toString()
}