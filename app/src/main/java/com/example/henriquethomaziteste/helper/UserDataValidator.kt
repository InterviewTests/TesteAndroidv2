package com.example.henriquethomaziteste.helper


import java.lang.StringBuilder
import java.text.NumberFormat

object UserDataValidator {

    fun validateInput(input: String) : Boolean{
        if (!input.matches(Regex(".*[A-Z].*"))) return false
        if (!input.matches(Regex(".*[a-z].*"))) return false
        if (!input.matches(Regex(".*\\d.*"))) return false
        return true
    }

    fun formatBankAccount (agency: String, accnt: String): String{
        val builder = StringBuilder(agency)
        builder.insert(agency.length - 1, "-")
        builder.insert(2, ".")

        return accnt + " / " + builder.toString()
    }

    fun formatBalance (value: Double): String{

        val format = NumberFormat.getCurrencyInstance();
        val currency = format.format(value);

        return "$currency"
    }
}