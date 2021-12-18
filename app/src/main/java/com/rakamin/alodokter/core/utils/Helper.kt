package com.rakamin.alodokter.core.utils

import android.annotation.SuppressLint
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.time.format.DateTimeFormatter

object Helper {

    fun convertToCurrency(balance: Int?): String {
        val localeID = Locale("in", "ID")

        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        val newBalance = numberFormat.format(balance)

        return newBalance.substring(0, newBalance.length - 3)
    }

    fun dateFormatter(time: String) = time.substring(0..9)

    fun dateToDDMMYYY(InputDate: String) : String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd MMM yyyy")
        val date = inputFormat.parse(InputDate)
        return outputFormat.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun dateToDDMMYYYWithDay(day: String, InputDate: String) : String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd MMM yyyy")
        val date = inputFormat.parse(InputDate)
        return "$day, ${outputFormat.format(date)}"
    }

}