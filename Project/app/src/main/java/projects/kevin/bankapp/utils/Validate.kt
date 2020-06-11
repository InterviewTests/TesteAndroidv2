package projects.kevin.bankapp.utils

import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog


fun validateLogin(password: String, login: String): Boolean {
    val regexAlpha = "[a-zA-z0-9]".toRegex()
    val regexSpecial = "[!@#\$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]".toRegex()
    val regexUpper = "[A-Z]".toRegex()

    if(login.isEmpty() || password.length < 2) {
        return false
    }

    if(regexAlpha.containsMatchIn(password)) {
        if(regexUpper.containsMatchIn(password)) {
            if(regexSpecial.containsMatchIn(password)) {
                return true
            }
        }
    }

    return false
}

fun validateMaterialDialog(activity: AppCompatActivity?): MaterialDialog? {
    if (activity?.isFinishing == false) {
        return MaterialDialog(activity)
    }

    return null
}


