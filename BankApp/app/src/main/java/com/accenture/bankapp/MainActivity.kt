package com.accenture.bankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.i("onCreate: Creating the Main Activity")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}