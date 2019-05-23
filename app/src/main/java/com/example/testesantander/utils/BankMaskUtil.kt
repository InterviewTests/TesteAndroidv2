package com.example.testesantander.utils

class BankMaskUtil{
    companion object {
        fun addChar(str: String, ch: Char, position: Int): String {
            val sb = StringBuilder(str)
            sb.insert(position, ch)
            return sb.toString()
        }
    }
}