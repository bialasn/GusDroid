package com.nbprog.gusdroid.util

import android.text.Html
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.extractFromTag(tagName : String): String {
    val p: Pattern = Pattern.compile("<$tagName>(.+?)</$tagName>", Pattern.DOTALL)
    val m: Matcher = p.matcher(this)
    m.find()
    return m.group(1).orEmpty()
}

fun String.readAsHTML(): String {
    return Html.fromHtml(this).toString()
}