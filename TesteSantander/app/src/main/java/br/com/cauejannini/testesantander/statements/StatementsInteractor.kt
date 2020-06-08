package br.com.cauejannini.testesantander.statements

import android.content.Context
import br.com.cauejannini.testesantander.commons.integracao.ApiRepository
import br.com.cauejannini.testesantander.login.UserAccount
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface StatementsInteractorInput {
    fun fetchStatements(userAccount: UserAccount?)
}

class StatementsInteractor: StatementsInteractorInput{

    var output: StatementsPresenterInput? = null
    val api = ApiRepository().get()

    override fun fetchStatements(userAccount: UserAccount?) {

        output?.presentUserAccount(userAccount)

        val userId = userAccount?.userId
        if (userId != null) {

            api.getStatements(userId).enqueue(object: Callback<StatementsResponseModel> {

                override fun onFailure(call: Call<StatementsResponseModel>, t: Throwable) {
                    output?.presentStatementsFailed(if (t.message != null) t.message!! else "Erro")
                }

                override fun onResponse(call: Call<StatementsResponseModel>, response: Response<StatementsResponseModel>) {
                    val statementsResponse = response.body()
                    if (statementsResponse != null) {
                        output?.presentStatementsResponse(statementsResponse)
                    } else {
                        output?.presentStatementsFailed(response.message())
                    }
                }
            })

        } else {
            output?.presentStatementsFailed("User id nulo")
        }


    }
}