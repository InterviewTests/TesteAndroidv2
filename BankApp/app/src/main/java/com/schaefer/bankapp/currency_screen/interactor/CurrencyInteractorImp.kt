package com.schaefer.bankapp.login_screen.interactor

import android.content.Context
import com.schaefer.bankapp.model.statement.StatementResult
import com.schaefer.bankapp.service.ApiProvider
import com.schaefer.bankapp.service.ApiProviderImp
import com.schaefer.bankapp.util.Constants
import com.schaefer.bankapp.util.error.ErrorHandler
import com.schaefer.bankapp.util.shared_preferences.SharedUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyInteractorImp : CurrencyInteractorInput {
    override fun getListStatement(
        userId: Int,
        context: Context,
        finishedListener: CurrencyInteractorInput.FinishedListener
    ) {
        val call = ApiProviderImp.connection.create(ApiProvider::class.java).statementList(userId)
        call.enqueue(object : Callback<StatementResult> {
            override fun onResponse(call: Call<StatementResult>, response: Response<StatementResult>) {
                if (response.raw().isSuccessful) {
                    response.body()?.let { finishedListener.successGetListStatement(it) }
                } else {
                    try {
                        response.body()?.errors?.let { finishedListener.errorGetListStatement(it) }
                    } catch (e: Exception) {
                        finishedListener.genericError(ErrorHandler(1).getMessageFromCode())
                    }
                }
            }

            override fun onFailure(call: Call<StatementResult>, t: Throwable) {
                finishedListener.genericError(ErrorHandler(2).getMessageFromCode())
            }
        })
    }

    override fun logout(context: Context, listener: CurrencyInteractorInput.FinishedListener) {
        try {
            SharedUtils.deleteSharedPreference(Constants.SHARED_LOGIN, context)
            listener.successLogout()
        } catch (ex: Exception) {
            listener.errorLogout()
        }
    }
}