package com.onlinepos.testcase.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale

fun colorFromHex(hex: String): Color {
    val cleanHex = hex.removePrefix("#")
    val parsed = cleanHex.toLongOrNull(16) ?: 0xCCCCCC
    return if (cleanHex.length == 6) {
        Color((0xFF shl 24) or parsed.toInt()) // add full alpha
    } else {
        Color(parsed.toInt())
    }
}

fun formatPrice(price: Double): String =
    price.toString().takeWhile { it != '.' } +
            "." +
            (price.toString().substringAfter(".", "00") + "00").take(2)



