package br.com.vinicius.bankapp.internal

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object CpfMask {
    const val maskCPF = "###.###.###=##"

    private fun unMask(string: String):String = string.replace("[^0-9]*", "")

    private fun replaceChars(cpfFull : String) : String {
        return cpfFull.replace(".", "").replace("-", "")
            .replace("(", "").replace(")", "")
            .replace("/", "").replace(" ", "")
            .replace("*", "")
    }
}