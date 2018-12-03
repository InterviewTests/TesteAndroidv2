package com.example.thiagoevoa.bank.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class Mask {
    companion object {
        val FORMAT_CPF = "###.###.###-##"
        val FORMAT_PHONE = "(###)####-#####"
        val FORMAT_CEP = "#####-###"
        val FORMAT_DATE = "##/##/####"
        val FORMAT_HOUR = "##:##"

        private fun unMask(s: String):String{
            return s.replace(".", "")
                .replace("-", "")
                .replace("/", "")
                .replace("(", "")
                .replace(" ","")
                .replace(":", "")
                .replace(")", "")
        }
    }

    fun mask (editText: EditText, mask: String): TextWatcher = object :TextWatcher{
        var isUpdating: Boolean = false
        var oldText = ""
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(isNumber(s.toString())){
                val str = unMask(s.toString())
                var myMask = ""

                if(isUpdating){
                    oldText = str
                    isUpdating = false
                    return
                }

                var i = 0
                for(m in mask.toCharArray()){
                    if(m != '#' && str.length > oldText.length){
                        myMask += m
                        continue
                    }
                    try {
                        myMask += str[i]
                    }catch (ex: Exception){
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(myMask)
                editText.setSelection(myMask.length)
            }
        }
    }
}