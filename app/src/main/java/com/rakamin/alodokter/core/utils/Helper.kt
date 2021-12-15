package com.rakamin.alodokter.core.utils

import java.time.format.DateTimeFormatter

object Helper {
    fun dateFormatter(time: String): String {
        return time.substring(0..9)
    }
}