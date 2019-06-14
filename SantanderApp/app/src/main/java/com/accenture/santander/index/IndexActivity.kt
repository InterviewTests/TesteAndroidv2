package com.accenture.santander.index

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.accenture.santander.R
import kotlinx.coroutines.launch

class IndexActivity : AppCompatActivity(), IndexContracts.IndexPresenterOutput {

    private lateinit var iIndexPresenterInput: IndexContracts.IndexPresenterInput
    private val activity: IndexActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            iIndexPresenterInput = IndexPresenter(
                activity,
                activity
            )

            iIndexPresenterInput.auth()
        }

    }

    override fun next() {
        print(true)
    }
}
