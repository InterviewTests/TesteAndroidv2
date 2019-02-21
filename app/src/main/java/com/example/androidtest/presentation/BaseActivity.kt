package com.example.androidtest.presentation

import android.content.Context
import android.support.v7.app.AppCompatActivity


interface BaseActivityContract {
    fun getContext(): Context
    fun getStringRes(stringId: Int): String
}

abstract class BaseActivity : AppCompatActivity(), BaseActivityContract {
    override fun getContext() = this
    override fun getStringRes(stringId: Int) = getString(stringId) ?: ""
}
