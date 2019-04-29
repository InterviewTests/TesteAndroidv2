package br.com.alex.bankappchallenge

import android.app.Application
import com.orhanobut.hawk.Hawk

class BankAppChallengeApplicationTest : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}