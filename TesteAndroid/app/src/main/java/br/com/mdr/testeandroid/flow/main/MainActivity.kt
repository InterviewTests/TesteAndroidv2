package br.com.mdr.testeandroid.flow.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.mdr.testeandroid.databinding.ActivityMainBinding
import com.santander.uma.flow.main.LoadingPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val activityPresenter: LoadingPresenter by inject()
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setupObservables()
    }

    private fun setupObservables() {
        activityPresenter.isLoading.observe(this, Observer {
            GlobalScope.launch {
                withContext(Dispatchers.Main) { mainBinding.showLoading = it }
            }
        })
    }
}