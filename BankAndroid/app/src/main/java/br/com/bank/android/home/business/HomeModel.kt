package br.com.bank.android.home.business

import br.com.bank.android.core.retrofit.stataments.IBankStatamentsService
import br.com.bank.android.home.presentation.data.Stataments

class HomeModel(private val bankStatamentsService: IBankStatamentsService) : HomeBusiness {

    override suspend fun getStataments(userId: Int): List<Stataments> {
        val statementsResponse = bankStatamentsService.getStataments(userId)
        val statements = ArrayList<Stataments>()
        statementsResponse.forEach {
            statements.add(Stataments(it.title, it.desc, it.date, it.value))
        }
        return statements
    }
}