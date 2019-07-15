package br.com.teste.santander.principal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.teste.santander.R


class ActivityPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    override fun onBackPressed() {
        finish()
    }
}
