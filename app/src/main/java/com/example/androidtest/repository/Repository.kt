package com.example.androidtest.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.androidtest.api.ServiceManager
import com.example.androidtest.api.ServiceResponseException
import com.example.androidtest.api.serviceCall
import java.text.ParseException


object Repository {


    fun loginCall(context: Context, user: String, pass: String, callback: (apiResponse: ApiResponse) -> Unit) {

        ServiceManager.getApi().postLogin(user, pass).serviceCall(
            {
                checkinAccount(context, user, pass, UserAccount(it.userAccount))
                callback(SuccessResponse())
            },
            {
                when (it) {
                    is ServiceResponseException,
                    is NullPointerException,
                    is ParseException -> callback(FailureResponse("Erro ao processar a resposta do Servidor."))
                    else -> callback(FailureResponse("Não foi possível realizar o Login."))
                }
            }
        )
    }


    fun getRecentStatements(context: Context, callback: (ApiResponse, ArrayList<Statement>?) -> Unit) {
        val userAccount = getLoggedAccount(context)!!

        ServiceManager.getApi().getStatements(userAccount.userId).serviceCall(
            { response ->
                val result = ArrayList<Statement>().apply {
                    response.statementList.forEach { add(Statement(it)) }
                }
                callback(SuccessResponse(), result)
            }, {
                when (it) {
                    is ServiceResponseException,
                    is NullPointerException,
                    is ParseException -> callback(FailureResponse("Erro ao processar a resposta do Servidor."), null)
                    else -> callback(FailureResponse("Não foi possível realizar o Login."), null)
                }
            }
        )
    }

    fun getLoggedAccount(context: Context): UserAccount? {
        val sp = context.getSharedPreferences("Login", MODE_PRIVATE)
        return UserAccount(
            userId = sp.getInt("userId", -1),
            name = sp.getString("name", null) ?: "",
            bankAccount = sp.getString("bankAccount", null) ?: "",
            agency = sp.getString("agency", null) ?: "",
            balance = sp.getFloat("balance", 0f).toDouble()
        )
    }

    fun logoff(context: Context) {
        checkoutAccount(context)
//        goToLoginActivity(context)
    }

    @SuppressLint("ApplySharedPref")
    private fun checkinAccount(context: Context, user: String, pass: String, userAccount: UserAccount) {
        context.getSharedPreferences("Login", MODE_PRIVATE).edit()
            .putString("user", user)
            .putString("pass", pass)
            .putInt("userId", userAccount.userId)
            .putString("name", userAccount.name)
            .putString("bankAccount", userAccount.bankAccount)
            .putString("agency", userAccount.agency)
            .putFloat("balance", userAccount.balance.toFloat())
            .commit()
    }

    private fun checkoutAccount(context: Context) {
        context.getSharedPreferences("Login", MODE_PRIVATE).edit()
            .remove("user")
            .remove("pass")
            .remove("userId")
            .remove("name")
            .remove("bankAccount")
            .remove("agency")
            .remove("balance")
            .apply()
    }
}



