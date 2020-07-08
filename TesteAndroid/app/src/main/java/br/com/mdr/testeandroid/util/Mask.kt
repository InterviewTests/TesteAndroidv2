package br.com.mdr.testeandroid.util

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText


class MaskUtil{
    enum class MaskType(val maskType: String) {
        CPF("CPF")
    }
    companion object {
        const val maskCPF = "###.###.###-##"

        fun removeMask(cpfFull : String) : String{
            return cpfFull.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/", "").replace(" ", "")
                .replace("*", "")
        }

        fun getCpfMask(_cpf: String): String {
            val mask = maskCPF
            var mascara = ""

            var i = 0
            for (m : Char in mask.toCharArray()){
                if ((m != '#') && _cpf.length != i){
                    mascara += m
                    continue
                }
                try {
                    mascara += _cpf[i]
                }catch (e : Exception){
                    break
                }
                i++
            }

            return mascara
        }
    }
}