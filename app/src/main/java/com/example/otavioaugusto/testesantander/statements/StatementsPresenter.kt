package com.example.otavioaugusto.testesantander.statements

import android.content.Context
import android.content.Intent
import com.example.otavioaugusto.testesantander.login.LoginActivity
import com.example.otavioaugusto.testesantander.model.UserAccount

class StatementsPresenter {

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
    }

