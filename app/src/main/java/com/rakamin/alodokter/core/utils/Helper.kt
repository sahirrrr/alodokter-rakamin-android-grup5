package com.rakamin.alodokter.core.utils

import java.text.NumberFormat
import java.util.*
import java.time.format.DateTimeFormatter

object Helper {

    fun convertToCurrency(balance: Int?): String {
        val localeID =  Locale("in", "ID")

        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        val newBalance = numberFormat.format(balance)

        return newBalance.substring(0, newBalance.length - 3)


    fun dateFormatter(time: String) = time.substring(0..9)
}