package br.com.rms.bankapp.utils

class Mask {

    fun addMask(textFormmat: String, mask: String): String {
        var text_formmat = ""
        var i = 0
        for (m in mask.toCharArray()) {
            if (m != '#') {
                text_formmat += m
                continue
            }

            try {
                text_formmat += textFormmat[i]
            } catch (e: Exception) {
                break
            }

            i++
        }
        return text_formmat
    }
}