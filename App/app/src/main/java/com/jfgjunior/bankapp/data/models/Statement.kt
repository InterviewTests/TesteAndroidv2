package com.jfgjunior.bankapp.data.models

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "Statement"

data class Statement(
    val title: String,
    @SerializedName(value = "desc")
    val description: String,
    val date: String,
    val value: Float
) {
    val formattedDate: String
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            try {
                dateFormat.parse(date)?.let { convertedDate ->
                    val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    return newFormat.format(convertedDate)
                }
            } catch (e: ParseException) {
                return date
            }
            return date
        }
}