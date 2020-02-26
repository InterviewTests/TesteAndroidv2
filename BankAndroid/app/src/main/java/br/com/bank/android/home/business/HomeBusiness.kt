package br.com.bank.android.home.business

import br.com.bank.android.home.presentation.data.Stataments

interface HomeBusiness {
    suspend fun getStataments(userId: Int): List<Stataments>
}