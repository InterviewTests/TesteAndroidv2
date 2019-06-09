package com.schaefer.bankapp.login_screen.router

import android.content.Intent

interface LoginRouterInput {
    fun passDataToNextScene(intent: Intent)
}