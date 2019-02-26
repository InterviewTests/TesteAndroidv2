package com.example.ilealnod.SantanderProjectKotlin.Todos.Login

import android.content.Context
import android.widget.Toast
import com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi.GetCredentials
import com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi.WebServices
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Classe onde se localiza toda a logica da Login ACTIVITY

class LoginAux(var loginActivity: LoginActivity) {

    private var loginValidate: LoginValidate? = LoginValidate()
    private var clientData: ClientData? = null
    private var accountInfo: List<AccountInfo>? = null

    init {
        clientData = ClientData()
        accountInfo = listOf(AccountInfo())
    }

    // Função para verificar os dados imputados no Login e Senha

    fun checkLogin(user: String, pass: String): Boolean {

        return if (checkUserField(user) || checkUserField(pass)) {
            showToast(loginActivity, "Erro: Empty Field")
            false

        } else if (!loginValidate!!.isValid(user)) {
            showToast(loginActivity, "Erro: User Formate Error")
            false

        } else if (!loginValidate!!.checkPassword(pass)) {
            showToast(loginActivity, "Erro: Password Formate Error")
            false

        } else true
    }

    // Função para mostrar alertas na tela

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    // Funcção que verificar se algum dos campos estao vazios

    fun checkUserField(field: String): Boolean {
        return field.isEmpty()
    }

    // Função que pega os Dados do cliente da APi

    fun requestClientData(service: GetCredentials, userInput: UserInput) {

        service.verifyLogin(userInput.login!!, userInput.password!!).enqueue(object : Callback<LoginResponse> {

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                Toast.makeText(loginActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                clientData = response.body()?.clientData
                requestAccountInfoData(clientData!!)

            }

        })
    }

    // Função que pega os Dados da Conta da APi

    fun requestAccountInfoData(clientData: ClientData) {

        WebServices().getAccountInfo(clientData.userId!!).enqueue(object : Callback<AccountInfoResponse> {

            override fun onFailure(call: Call<AccountInfoResponse>, t: Throwable) {
                Toast.makeText(loginActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AccountInfoResponse>, response: Response<AccountInfoResponse>) {

                accountInfo = response.body()?.list
                loginActivity.nextPage(accountInfo, clientData)
            }
        })
    }

}