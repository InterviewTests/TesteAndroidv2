package br.com.bank.android.core.retrofit.stataments

import br.com.bank.android.core.retrofit.stataments.response.StatamentsResponse

interface IBankStatamentsService {
    suspend fun getStataments(idUser: Int): List<StatamentsResponse>
}