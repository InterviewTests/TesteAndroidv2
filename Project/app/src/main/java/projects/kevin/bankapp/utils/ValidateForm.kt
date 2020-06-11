package projects.kevin.bankapp.utils

import android.app.Activity


fun validatePassword(pass: String, act: Activity): Boolean {
    val regexAlpha = "[a-zA-z0-9]".toRegex()
    val regexSpecial = "[!@#\$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]".toRegex()
    val regexUpper = "[A-Z]".toRegex()
        if(regexAlpha.containsMatchIn(pass)) {
            if(regexUpper.containsMatchIn(pass)) {
                if(regexSpecial.containsMatchIn(pass)) {
                    return true
                }
            }
        }

    return false
}


