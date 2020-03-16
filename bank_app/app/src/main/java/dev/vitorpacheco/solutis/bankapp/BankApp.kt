package dev.vitorpacheco.solutis.bankapp

import android.app.Application

class BankApp : Application() {

    companion object {
        const val SHARED_PREFERENCES_KEY = "bank_app_preferences"
        const val LAST_LOGGED_USER_KEY = "last_logged_user"
        const val API_BASE_URL = "https://bank-app-test.herokuapp.com/api/"
        const val APP_INTENT_ACCOUNT_EXTRA = "account"
    }

}