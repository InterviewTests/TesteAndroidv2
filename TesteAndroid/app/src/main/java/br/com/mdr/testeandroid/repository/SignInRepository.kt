package br.com.mdr.testeandroid.repository

import android.content.SharedPreferences
import br.com.mdr.testeandroid.api.SignInApi
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User
import com.google.gson.Gson

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
class SignInRepository(
    private val signInApi: SignInApi,
    private val preferences: SharedPreferences,
    private val preferencesEditor: SharedPreferences.Editor,
    private val gson: Gson
) : BaseRepository(gson), ISignInRepository {

    companion object {
        private const val USER_KEY = "logged_user"
    }

    override suspend fun signInUser(user: SignInApiModel): UserApiModel? {
        return handleResponse(signInApi.signInUser(user))
    }

    override fun saveLoggedUser(user: User) {
        val strUser = gson.toJson(user)
        preferencesEditor.putString(USER_KEY, strUser)
        preferencesEditor.apply()
    }

    override fun getLoggedUser(): User? {
        val strUser = preferences.getString(USER_KEY, "")
        return if (strUser != null) gson.fromJson(strUser, User::class.java) else null
    }
}