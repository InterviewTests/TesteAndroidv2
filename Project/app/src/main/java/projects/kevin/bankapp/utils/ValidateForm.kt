package projects.kevin.bankapp.utils

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog


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

fun validateMaterialDialog(activity: AppCompatActivity?): MaterialDialog? {
    if (activity?.isFinishing == false) {
        return MaterialDialog(activity)
    }

    return null
}


