package com.rafaelpereiraramos.testeandroidv2.db

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun convertToDate(s: String?): Date? {

        if (s.isNullOrEmpty()) {
            return null
        }

        if (s.length >= 14) {
            val format: DateFormat

            if (s.contains("T")) {
                format = SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss", Locale.getDefault())
            } else {
                format = SimpleDateFormat("YYYY-MM-DD HH:mm:ss", Locale.getDefault())
            }
            try {
                return format.parse(s)
            } catch (e: ParseException) {
                /* invalid format. will throw exception later */
            }

        } else if (s.length >= 8 && s.length <= 10) {
            try {
                return SimpleDateFormat("YYYY-MM-DD", Locale.getDefault()).parse(s)
            } catch (e: ParseException) {
                /* invalid format. will throw exception later */
            }

        }

        throw RuntimeException("Invalid Date")
    }
}