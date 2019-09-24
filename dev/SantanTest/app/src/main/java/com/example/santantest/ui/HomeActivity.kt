package com.example.santantest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.santantest.R
import com.example.santantest.domain.interactor.home.HomeInteractor
import com.example.santantest.domain.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val interactor = HomeInteractor()
    val presenter = HomePresenter(this@HomeActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setData()
        setEvents()
    }

    private fun setEvents() {
        btLogout.setOnClickListener {
            presenter.logout()
        }
    }

    fun setData() {
        presenter.setHeaderData()
        presenter.getUserData().userId?.let {
            interactor.getStatements(it, presenter)
        }
    }
}
