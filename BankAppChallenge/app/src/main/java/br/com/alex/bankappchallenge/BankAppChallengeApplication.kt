package br.com.alex.bankappchallenge

import android.app.Application
import com.orhanobut.hawk.Hawk

class BankAppChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupHawk()
    }

    private fun setupHawk() = Hawk.init(this).build()
}