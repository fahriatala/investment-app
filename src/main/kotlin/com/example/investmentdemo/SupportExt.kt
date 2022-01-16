package com.example.investmentdemo

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun Double.roundOffDecimal(): Double {
    val decimalFormat = DecimalFormat("#.####").apply { roundingMode = RoundingMode.FLOOR }
    return decimalFormat.format(this).toDouble()
}

fun Date?.convert(
        pattern: String = "yyyy-MM-dd HH:mm:ss",
        locale: Locale = Locale.getDefault(Locale.Category.FORMAT)
): String = when (this) {
    null -> ""
    else -> SimpleDateFormat(pattern, locale).format(this)
}

fun Double?.toCurrencyFormat(): String {
    if (this == null) {
        return ""
    }

    val formatSymbol = DecimalFormatSymbols().apply {
        currencySymbol = "Rp "
        monetaryDecimalSeparator = ','
        groupingSeparator = '.'
    }

    val rupiahFormat = (DecimalFormat.getCurrencyInstance() as DecimalFormat).apply {
        decimalFormatSymbols = formatSymbol
    }

    return rupiahFormat.format(this)
}
