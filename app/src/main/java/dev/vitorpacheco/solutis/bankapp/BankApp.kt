package dev.vitorpacheco.solutis.bankapp

import android.app.Application

class BankApp : Application() {

    companion object {
        const val SHARED_PREFERENCES_KEY = "bank_app_preferences"
        const val LAST_LOGGED_USER_KEY = "last_logged_user"
    }

}