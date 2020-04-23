package com.tata.bank.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tata.bank.R

interface MainActivityInput {

}

class MainActivity : AppCompatActivity(), MainActivityInput {
    lateinit var output: MainInteractorInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
