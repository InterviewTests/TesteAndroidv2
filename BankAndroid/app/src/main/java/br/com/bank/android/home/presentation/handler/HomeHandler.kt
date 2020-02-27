package br.com.bank.android.home.presentation.handler

import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.home.presentation.data.Stataments

interface HomeHandler {
    fun onDisconnected()
    fun setLoading(loading: Boolean)
    fun onError(error: BusinessError)
    fun onStataments(stataments: List<Stataments>)
}