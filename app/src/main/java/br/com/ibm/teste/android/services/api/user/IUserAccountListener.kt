package br.com.ibm.teste.android.services.api.user

import br.com.ibm.teste.android.services.models.UserResponse

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:36
 */
interface IUserAccountListener {
    fun showLoading()
    fun hideLoading()
    fun responseError(messageError: String)
    fun responseSuccess(userResponse: UserResponse)
}