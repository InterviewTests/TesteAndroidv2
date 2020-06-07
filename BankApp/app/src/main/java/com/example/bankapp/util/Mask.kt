package com.example.bankapp.util

class Mask {
    fun addMask(textoAFormatar: String, mask: String): String? {
        var formatado: String? = ""
        var i = 0
        for (m in mask.toCharArray()) {
            if (m != '#') {
                formatado += m
                continue
            }
            formatado += try {
                textoAFormatar[i]
            } catch (e: Exception) {
                break
            }
            i++
        }
        return formatado
    }
}