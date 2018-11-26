package com.rafaelpereiraramos.testeandroidv2.view.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelpereiraramos.testeandroidv2.R

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
    }

    companion object {
        const val USER_KEY = "userKey"
    }
}
