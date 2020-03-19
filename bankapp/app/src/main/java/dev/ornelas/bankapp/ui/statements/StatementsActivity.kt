package dev.ornelas.bankapp.ui.statements

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.ornelas.bankapp.R
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }
}
