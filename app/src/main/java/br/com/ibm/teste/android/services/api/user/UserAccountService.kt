package br.com.ibm.teste.android.services.api.user

import br.com.ibm.teste.android.app.IbmTestApplication
import br.com.ibm.teste.android.services.config.APIClient
import br.com.ibm.teste.android.services.models.UserRequest
import br.com.ibm.teste.android.services.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:37
 */
class UserAccountService (private val userAccountListener: IUserAccountListener) : IUserAccountService {

    private var apiClient: APIClient = IbmTestApplication.getInstance().getApiClient()
    private val userAccountApi = this.apiClient.getRetrofit().create(UserAccountAPI::class.java)


    override fun login(userRequest: UserRequest) {
        userAccountListener.showLoading()
        val callRequest = userAccountApi.login(userRequest)

        callRequest.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                userAccountListener.hideLoading()
                if (response.isSuccessful && response.body() != null && (response.body()?.error != null)) {
                    userAccountListener.responseSuccess(response.body()!!)
                } else {
                    response.body()?.error?.message?.let { userAccountListener.responseError(it) }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userAccountListener.hideLoading()
                t.message?.let { userAccountListener.responseError(it) }
            }
        })
    }
}