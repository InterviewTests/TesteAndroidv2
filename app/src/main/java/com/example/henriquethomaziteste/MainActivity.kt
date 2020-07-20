package com.example.henriquethomaziteste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.henriquethomaziteste.helper.EventBus

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.unregister(this)
    }


}