package br.com.ibm.teste.android.services.api.user

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:34
 */
interface IUserAccountService {

    /**
     * The service for login in your bank account
     */
    fun login(user: String, password: String)
}