package br.com.flaviokreis.santanderv2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.flaviokreis.santanderv2.data.model.UserAccount
import br.com.flaviokreis.santanderv2.data.repositories.LoginRepository
import br.com.flaviokreis.santanderv2.data.repositories.StatementRepository
import br.com.flaviokreis.santanderv2.data.response.StatementsResponse
import javax.inject.Inject

class StatementViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val repository: StatementRepository
) : ViewModel() {

    fun getStatements(): LiveData<StatementsResponse> {
        return repository.getStatements()
    }

    val userAccount : LiveData<UserAccount>
        get() = loginRepository.getUserAccount()

    fun hasUserAccount(): LiveData<Boolean> {
        return loginRepository.hasUserAccount()
    }

    fun logout() {
        loginRepository.logout()
    }
}