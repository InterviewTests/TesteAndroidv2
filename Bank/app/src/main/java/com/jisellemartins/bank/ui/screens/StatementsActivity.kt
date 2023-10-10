package com.jisellemartins.bank.ui.screens

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jisellemartins.bank.R
import com.jisellemartins.bank.databinding.ActivityStatementsBinding
import com.jisellemartins.bank.model.Statements
import com.jisellemartins.bank.network.Output
import com.jisellemartins.bank.ui.adapter.ListStatementsAdapter
import com.jisellemartins.bank.viewmodel.StatementsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class StatementsActivity : AppCompatActivity() {

    val statementsViewModel: StatementsViewModel by viewModel()
    private lateinit var binding: ActivityStatementsBinding
    private lateinit var adapter: ListStatementsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statementsViewModel.statementsLiveData.observe(this) {
            binding.isloading = false
            when (it) {
                is Output.Success -> {
                    if (!it.data?.id.equals("0")) {
                        binding.textUser.text = it.data?.user?.user
                        binding.textAccount.text = it.data?.account
                        binding.textBalance.text = "R$ " + it.data?.balance.toString() + ",00"
                        initRecycleView(it.data?.statementsList ?: emptyList())
                    } else {
                        showToast("Não foi possível carregar os dados", this)
                    }
                }
                is Output.Error ->{
                    showToast("Não foi possível carregar os dados", this)
                }
                is Output.ErrorT -> {
                    it.error.message?.let { error -> showToast(error, this) }

                }
                else -> binding.isloading = true
            }
        }

        val sharedPreference =  getSharedPreferences(getString(R.string.data_bank), Context.MODE_PRIVATE)
        val s = sharedPreference.getString(getString(R.string.idUser), "")
        statementsViewModel.getDetails(s.toString())

    }

    private fun initRecycleView(statements: List<Statements>) {
        adapter = ListStatementsAdapter(statements)
        binding.listStatements.adapter = adapter
        binding.listStatements.layoutManager = LinearLayoutManager(this)
    }
    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}