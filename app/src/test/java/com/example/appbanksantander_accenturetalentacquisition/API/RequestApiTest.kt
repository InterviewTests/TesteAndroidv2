package com.example.appbanksantander_accenturetalentacquisition.API

import com.example.appbanksantander_accenturetalentacquisition.Model.LoginResponse
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementResponse
import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel
import junit.framework.TestCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestApiTest : TestCase() {

    val apiClient: ApiClient = ApiClient()
    val retrofitEndPoint = apiClient.getClient().create(RetrofitEndPoint::class.java)
    val responseOK: Int = 200

    fun testGetAccountInfo() {
        val callLogin =  retrofitEndPoint.loginUser()
        callLogin.enqueue(object: Callback<UserAccountModel> {
            override fun onResponse(call: Call<UserAccountModel>, response: Response<UserAccountModel>) {
                if (response.code() == responseOK){
                    val userAccountModel: UserAccountModel? = response.body()
                    System.out.println(userAccountModel)
                }
            }

            override fun onFailure(call: Call<UserAccountModel>, t: Throwable) {
                System.out.println("Error: " + t.toString())
            }
        })
    }

    fun testGetStatement() {
        val idUser = 1
        val callStatement =  retrofitEndPoint.getStatements(idUser)
        callStatement.enqueue(object: Callback<StatementResponse> {
            override fun onResponse(call: Call<StatementResponse>, response: Response<StatementResponse>) {
                if (response.code() == responseOK){
                    val statementModel: List<StatementModel> = response.body()!!.statementListResult
                    System.out.println(statementModel)
                }
            }

            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                System.out.println("Error: " + t.toString())
            }
        })
    }
}