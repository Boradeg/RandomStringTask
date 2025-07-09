package com.app.randomstringtask.utils

import android.util.Log
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale


fun convertIsoToFormatted(input: String?): String {
    return try {
        Log.d("TAG",input.toString())
        if (input.isNullOrBlank()) return "Invalid date"
        val zonedDateTime = ZonedDateTime.parse(input)
            .withZoneSameInstant(java.time.ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.getDefault())
        zonedDateTime.format(formatter)
    } catch (e: DateTimeParseException) {
        "Invalid date"
    } catch (e: Exception) {
        "Error: ${e.localizedMessage}"
    }
}
fun String.toSafeIntOrNull(): Int? {
    return if (this.matches(Regex("^[0-9]+$"))) {
        this.toIntOrNull()
    } else {
        null
    }
}