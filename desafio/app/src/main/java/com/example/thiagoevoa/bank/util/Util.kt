package com.example.thiagoevoa.bank.util

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun checkUserField(field: String): Boolean {
    return field.isNullOrEmpty()
}

fun checkPasswordField(field: String): Boolean {
    val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$".toRegex()
    return regex.matches(field)
}

fun formateDate(date: String): String {
    return date.replace("-", "/")
}

fun isNumber(text: String): Boolean{
    val regex = "^[0-9]*\$".toRegex()
    return regex.matches(text.replace(".","").replace("-",""))
}