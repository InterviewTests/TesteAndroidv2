package com.example.otavioaugusto.testesantander.statements

import android.content.Context
import android.content.Intent
import com.example.otavioaugusto.testesantander.utils.APIService
import com.example.otavioaugusto.testesantander.model.StatementListItem
import com.example.otavioaugusto.testesantander.model.Statements
import com.example.otavioaugusto.testesantander.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementsPresenter (val view:StatementsActivity): StatementsContrato.Presenter {


    companion object {
        fun dadosParaIntent( userId: Int,  name:String,  bankAccount:String,  agency:String,  balance:Double,  contexto: Context){
            val intent = Intent(contexto, StatementsActivity::class.java)
            intent.putExtra("userId", userId)
            intent.putExtra("name",name)
            intent.putExtra("bankAccount", bankAccount)
            intent.putExtra("agency", agency)
            intent.putExtra("balance", balance)
            contexto.startActivity(intent)
        }
        }

    override fun obterListadaAPI(id:String) {

        var call = RetrofitService
            .retrofit
            .create(APIService::class.java)
            .getStatements(id)

        call.enqueue(object : Callback<Statements> {
            override fun onFailure(call: Call<Statements>, t: Throwable) {

                view.mensagensErro(t.message.toString())


            }

            override fun onResponse(call: Call<Statements>, response: Response<Statements>) {

                val lista:List<Statements>
                val listItem:List<StatementListItem>

                if (response.isSuccessful){
                    listItem = response.body()!!.statementList!!


                    view.listaStatements(listItem)
                    view.mensagemOk(listItem.toString())
                }

                view.mensagemOk(response.code().toString())

            }

        })


    }
    }

