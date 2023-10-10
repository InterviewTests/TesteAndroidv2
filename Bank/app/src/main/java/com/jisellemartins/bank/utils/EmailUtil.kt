package com.jisellemartins.bank.utils

import androidx.core.util.PatternsCompat

class EmailUtil {

    companion object{
        fun CharSequence?.isValidEmail() =
            !isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
    }

}


