package com.accenture.bankapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}