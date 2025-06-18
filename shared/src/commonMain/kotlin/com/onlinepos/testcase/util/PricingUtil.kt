package com.onlinepos.testcase.util

fun formatPrice(price: Double): String =
    price.toString().takeWhile { it != '.' } +
            "," +
            (price.toString().substringAfter(".", "00") + "00").take(2)
