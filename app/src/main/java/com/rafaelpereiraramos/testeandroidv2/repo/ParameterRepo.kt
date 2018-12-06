package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.db.model.ParameterTO

/**
 * Created by Rafael P. Ramos on 06/12/2018.
 */
interface ParameterRepo {

    fun getLoginParameter(): LiveData<ParameterTO?>

    fun setLoggedParameter(userId: Int?)

    companion object {
        const val LOGGED_ID = "LOGGED_ID"
    }
}