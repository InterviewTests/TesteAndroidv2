package br.com.ibm.teste.android.ui.generics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 16:42
 */
abstract class GenericActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMethods()
    }

    private fun startMethods() {
        setLayout()
        loadingMethods()
    }

    protected fun setHomeButton() {
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    abstract fun setLayout()
    abstract fun loadingMethods()

}