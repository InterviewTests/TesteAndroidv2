package com.schaefer.bankapp.login_screen.router

import android.content.Intent

interface CurrencyRouterInput {
    fun passDataToNextScene(intent: Intent)
}