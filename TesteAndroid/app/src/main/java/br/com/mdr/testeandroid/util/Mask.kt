package br.com.mdr.testeandroid.util

import android.util.Log


class MaskUtil{

    companion object {
        private const val maskCPF = "###.###.###-##"

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
            Log.i("TesteAndroid", mascara)
            return mascara
        }
    }
}