package com.example.androidtest.presentation.currency

import android.os.Bundle
import com.example.androidtest.presentation.BaseActivity
import com.example.androidtest.presentation.BaseActivityContract
import com.example.androidtest.R


interface CurrencyActivityContract : BaseActivityContract {

}

class CurrencyActivity : BaseActivity(),
    CurrencyActivityContract {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

    }
}
