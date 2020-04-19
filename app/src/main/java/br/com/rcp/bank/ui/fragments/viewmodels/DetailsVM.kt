package br.com.rcp.bank.ui.fragments.viewmodels

import androidx.lifecycle.MutableLiveData
import br.com.rcp.bank.dto.StatementDTO
import br.com.rcp.bank.dto.StatementListDTO
import br.com.rcp.bank.repositories.AccountRepository
import br.com.rcp.bank.repositories.base.Repository
import br.com.rcp.bank.ui.fragments.viewmodels.base.AbstractVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsVM : AbstractVM<AccountRepository>() {
	val	collection	= MutableLiveData(mutableListOf<StatementDTO>())

	init {
		component.inject(this)
	}

	suspend fun getStatements(identifier: Long) {
		setLoading()
		when (val response = repository.getStatements(identifier)) {
			is Repository.Result.Success	-> handleSuccess(response.data)
			is Repository.Result.Failure	-> setToast("Falha ao obter dados!")
		}
	}

	private fun handleSuccess(response: Response<StatementListDTO>) {
		CoroutineScope(Dispatchers.Main).launch { collection.value = (response.body()?.list ?: mutableListOf()) as MutableList<StatementDTO>? }
		setNotLoading()
	}
}