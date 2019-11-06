package ru.meteor.myapplication.adapters

import java.text.SimpleDateFormat
import java.util.*

object Utilities {

    fun getAge(year: Int, month: Int, day: Int): String {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob.set(year, month, day)
        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        val ageInt = age

        return ageInt.toString()
    }

    fun parseDate(date: String): Calendar {
        val formatted = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val formatted2 = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val datePattern = "\\d{2}-\\d{2}-\\d{4}"
        val dateFormat: Date
        val isDate = date.matches(datePattern.toRegex())
        dateFormat = if (isDate) {
            formatted.parse(date)
        } else {
            formatted2.parse(date)
        }
        val c = Calendar.getInstance()
        c.time = dateFormat

        return c
    }

    fun firstUpperCase(word: String?): String {
        return (word?.substring(0, 1)?.toUpperCase()) + word?.toLowerCase()?.substring(1)
    }

    fun checkDateFormat(date: String): Boolean {
        val datePattern = "\\d{2}-\\d{2}-\\d{4}"
        return date.matches(datePattern.toRegex())
    }

    fun replaceDate(date: String): String {
        return date.replace("-", ".")
    }
}