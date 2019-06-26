package com.example.desafiosantander.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.desafiosantander.BankApplicationTest

class BankTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, BankApplicationTest::class.java.name, context)
    }

}