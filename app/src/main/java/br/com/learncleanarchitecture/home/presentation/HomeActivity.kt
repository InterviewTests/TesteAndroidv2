package br.com.learncleanarchitecture.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.learncleanarchitecture.R
import br.com.learncleanarchitecture.home.presentation.HomeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }

}
