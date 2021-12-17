package com.rakamin.alodokter.core.utils

import java.text.NumberFormat
import java.util.*

object Helper {

    fun convertToCurrency(balance: Int?): String {
        val localeID =  Locale("in", "ID")

        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        val newBalance = numberFormat.format(balance)

        return newBalance.substring(0, newBalance.length - 3)
    }
}