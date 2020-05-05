package com.paulokeller.bankapp.ui.statements

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paulokeller.bankapp.R

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container_statements, StatementsFragment.newInstance()).commitNow()
        }
    }
}
