package br.com.mdr.testeandroid.flow.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mdr.testeandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private val activityPresenter: LoadingPresenter by inject()
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        //setupObservables()
    }

//    private fun setupObservables() {
//        activityPresenter.isLoading.observe(this, Observer {
//            GlobalScope.launch {
//                withContext(Dispatchers.Main) { mainBinding.showLoading = it }
//            }
//        })
//    }

}