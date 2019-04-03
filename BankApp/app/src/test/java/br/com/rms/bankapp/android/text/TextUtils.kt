package br.com.rms.bankapp.android.text

object TextUtils {
    fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.length == 0
    }
}