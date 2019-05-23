package com.example.testesantander.utils

class EmailUtil{
    companion object {
        fun isValidEmail(email: String): Boolean{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}