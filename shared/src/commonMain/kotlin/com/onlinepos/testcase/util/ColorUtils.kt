package com.onlinepos.testcase.util

import androidx.compose.ui.graphics.Color

fun colorFromHex(hex: String): Color {
    val cleanHex = hex.removePrefix("#")
    val parsed = cleanHex.toLongOrNull(16) ?: 0xCCCCCC
    return if (cleanHex.length == 6) {
        Color((0xFF shl 24) or parsed.toInt()) // add full alpha
    } else {
        Color(parsed.toInt())
    }
}
