package com.example.testeandroidv2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testeandroidv2.R
import com.example.testeandroidv2.viewModel.StatementsViewModel
import kotlinx.android.synthetic.main.activity_statements.*

class StatementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        val viewModel: StatementsViewModel = ViewModelProviders.of(this).get(StatementsViewModel::class.java)

        viewModel.getStatements()
        viewModel.statementsLiveData.observe(this, Observer {

            it?.let { books ->
                with(recyclerBooks){
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                        this@StatementsActivity,
                        androidx.recyclerview.widget.RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = StatementsAdapter(books)
                }
            }
        })
    }
}
