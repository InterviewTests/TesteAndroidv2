package br.com.mdr.testeandroid.service

import android.content.Context
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User

interface ISignInService {

    /**
     * Sign in user to app
     * @param signInUser The user that will be created
     * @return The authenticated user
     * @throws <InvalidLoginThrowable> when user data is not valid for creation
     */
    suspend fun loginUser(signInUser: SignInApiModel): UserApiModel?

    /**
     * Verify if has user saved into SharedPreferences
     * @return user if yes, null otherwise
     */
    fun getLoggedUser(context: Context): User?

    /**
     * Save user logged into SharedPreferences
     */
    fun saveLoggedUser(user: User, context: Context)
}