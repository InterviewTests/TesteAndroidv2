package com.androiddeveloper.santanderTest.shared.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var baseInteractor: BaseInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseInteractor?.createCompositeDisposable()
    }

    override fun onStop() {
        super.onStop()
        baseInteractor?.unsubscribe()
    }
}