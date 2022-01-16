package com.example.investmentdemo

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(): Double {
    val decimalFormat = DecimalFormat("#.####").apply { roundingMode = RoundingMode.FLOOR }
    return decimalFormat.format(this).toDouble()
}