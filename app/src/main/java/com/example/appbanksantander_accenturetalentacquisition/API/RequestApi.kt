package com.example.appbanksantander_accenturetalentacquisition.API

import android.content.Context
import android.widget.Toast
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementResponse
import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestApi(private val context: Context?): ServiceApi {
    val apiClient: ApiClient = ApiClient()
    val retrofitEndPoint = apiClient.getClient().create(RetrofitEndPoint::class.java)
    val responseOK: Int = 200

    override fun getAccountInfo(callback: ServiceApi.ServiceApiCallbackUser<UserAccountModel>) {
        val callLogin = retrofitEndPoint.loginUser()
        callLogin.enqueue(object: Callback<UserAccountModel> {
            override fun onResponse(call: Call<UserAccountModel>, response: Response<UserAccountModel>) {
                if (response.code() == responseOK){
                    val userAccountModel: UserAccountModel = response.body()!!
                    callback.loaded(userAccountModel)
                }else{
                    Toast.makeText(context, "Error Account: " + response.code().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<UserAccountModel>, t: Throwable) {
                Toast.makeText(context, "Error Account: " + t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getStatement(callback: ServiceApi.ServiceApiCallbackStatement<StatementResponse>) {
        val callStatement = retrofitEndPoint.getStatements()
        callStatement.enqueue(object: Callback<StatementResponse>{
            override fun onResponse(call: Call<StatementResponse>, response: Response<StatementResponse>) {
                if (response.code() == responseOK){
                    val statementModel: List<StatementModel> = response.body()!!.statementListResult
                    callback.loaded(statementModel)
                }else{
                    Toast.makeText(context, "Error Statement: " + response.code().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                Toast.makeText(context, "Error Statement: " + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}