package br.com.mdr.testeandroid.repository

import android.content.Context
import br.com.mdr.testeandroid.api.SignInApi
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.util.Constants.Companion.PREFERENCES_FILE_KEY
import br.com.mdr.testeandroid.util.Constants.Companion.USER_KEY
import com.google.gson.Gson

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
class SignInRepository(
    private val signInApi: SignInApi,
    private val gson: Gson
) : BaseRepository(), ISignInRepository {

    override suspend fun signInUser(user: SignInApiModel): UserApiModel? {
        return handleResponse(signInApi.signInUser(user))
    }

    override fun saveLoggedUser(user: User, context: Context) {
        val editor = context.getSharedPreferences(PREFERENCES_FILE_KEY,  Context.MODE_PRIVATE).edit()
        val strUser = gson.toJson(user)
        editor.putString(USER_KEY, strUser)
        editor.apply()
    }

    override fun getLoggedUser(context: Context): User? {
        val preferences = context.getSharedPreferences(PREFERENCES_FILE_KEY,  Context.MODE_PRIVATE)
        val strUser = preferences.getString(USER_KEY, "")
        return if (strUser != null) gson.fromJson(strUser, User::class.java) else null
    }
}