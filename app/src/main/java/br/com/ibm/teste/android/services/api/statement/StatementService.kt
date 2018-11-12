package br.com.ibm.teste.android.services.api.statement

import br.com.ibm.teste.android.app.IbmTestApplication
import br.com.ibm.teste.android.services.config.APIClient
import br.com.ibm.teste.android.services.models.StatementsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by paulo.
 * Date: 12/11/18
 * Time: 11:43
 */
class StatementService(private val statementListener: IStatementListener) : IStatementService {

    private var apiClient: APIClient = IbmTestApplication.getInstance().getApiClient()
    private val userAccountApi = this.apiClient.getRetrofit().create(StatementAPI::class.java)

    override fun statements(userId: Int) {
        statementListener.showLoading()
        val callRequest = userAccountApi.statements(userId)

        callRequest.enqueue(object : Callback<StatementsResponse> {
            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                statementListener.hideLoading()
                if (response.isSuccessful && response.body() != null && (response.body()?.error != null)) {
                    statementListener.responseSuccess(response.body()!!)
                } else {
                    response.body()?.error?.message?.let { statementListener.responseError(it) }
                }
            }

            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {
                statementListener.hideLoading()
                t.message?.let { statementListener.responseError(it) }
            }
        })
    }
}