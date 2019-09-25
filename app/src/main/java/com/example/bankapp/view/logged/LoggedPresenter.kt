package com.example.bankapp.view.logged

import android.content.Context
import com.example.bankapp.api.BankAPIInterface
import com.example.bankapp.api.BankAPIService
import com.example.bankapp.model.Statement
import com.example.bankapp.model.User
import com.example.bankapp.pojo.statement.StatementPojo
import com.example.bankapp.util.SharedPrefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoggedPresenter(private val mView: LoggedContract.View): LoggedContract.Presenter {

    override fun getStatementList(user: User) {
        val apiInterface = BankAPIService.getClient().create(BankAPIInterface::class.java)

        val callws = apiInterface.getListStatements(user.userId!!)

        callws.enqueue(object : Callback<StatementPojo> {
            override fun onResponse(call: Call<StatementPojo>?, response: Response<StatementPojo>?) {
                val resource = response?.body()
                if (resource != null) {
                    val statements = resource!!.statementList!!.flatMap {
                        listOf(Statement(
                            it.title,
                            it.desc,
                            it.date,
                            it.value)
                        )
                    }
                    mView.updateStatements(statements)
                }
            }

            override fun onFailure(call: Call<StatementPojo>?, t: Throwable?) {
                //txtUser.setText("Não encontrado")
            }
        })
    }

    override fun logOut() {
        SharedPrefs.getInstance(mView as Context).logout()
        mView.exit()
    }

}