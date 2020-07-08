package br.com.mdr.testeandroid.repository

import android.content.SharedPreferences
import br.com.mdr.testeandroid.api.DashboardApi
import br.com.mdr.testeandroid.model.api.DashboardApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.util.Constants
import com.google.gson.Gson

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */
class DashboardRepository(
    private val dashboardApi: DashboardApi,
    private val preferencesEditor: SharedPreferences.Editor,
    gson: Gson
) : BaseRepository(gson), IDashboardRepository {

    override suspend fun getStatements(userId: Int): DashboardApiModel? {
        return handleResponse(dashboardApi.getStatements(userId.toString()))
    }

    override fun signOutUser(user: User) {
        preferencesEditor.remove(Constants.USER_KEY)
        preferencesEditor.apply()
    }
}