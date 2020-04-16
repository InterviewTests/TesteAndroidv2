package com.example.androidtest.utils

object Mask {

    fun addMask(mask: String, text: String): String {
        var i = 0
        var mascara = ""
        val str = unMask(text)
        val old = ""

        for (m in mask.toCharArray()) {
            if (m != '#' && str.length > old.length) {
                mascara += m
                continue
            }
            try {
                mascara += str[i]
            } catch (e: Exception) {
                break
            }
            i++
        }
        return mascara
    }

    fun unMask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
            .replace("[)]".toRegex(), "").replace(" ".toRegex(), "")
            .replace(",".toRegex(), "")
    }
}