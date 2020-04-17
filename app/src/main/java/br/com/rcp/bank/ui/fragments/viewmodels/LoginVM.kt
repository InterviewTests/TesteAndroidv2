package br.com.rcp.bank.ui.fragments.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.rcp.bank.R
import br.com.rcp.bank.database.models.Account
import br.com.rcp.bank.database.models.Login
import br.com.rcp.bank.dto.LoginResponseDTO
import br.com.rcp.bank.dto.UserAccountDTO
import br.com.rcp.bank.repositories.LoginRepository
import br.com.rcp.bank.repositories.base.Repository
import br.com.rcp.bank.ui.fragments.viewmodels.base.AbstractVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginVM : AbstractVM<LoginRepository>() {
	private	val	usernameObserver	= Observer<String> { onUsernameChanged(it) }
	private	val	passwordObserver	= Observer<String> { onPasswordChanged(it) }

	val	username		= MutableLiveData("")
	val	password		= MutableLiveData("")
	val	buttonEnabled	= ObservableBoolean(false)
	val	successful		= MutableLiveData<Account>(null)

	init {
		component.inject(this)
		username.observeForever(usernameObserver)
		password.observeForever(passwordObserver)
		getLastLoggedUser()
	}

	override fun onCleared() {
		username.removeObserver(usernameObserver)
		password.removeObserver(passwordObserver)
	}

	fun login() {
		setLoading()
		CoroutineScope(Dispatchers.IO).launch {
			when (val result = repository.login(username.value!!, password.value!!)) {
				is Repository.Result.Success 	-> onLoginSuccess(result.data)
				is Repository.Result.Failure 	-> handleLoginError(result.error)
			}
		}
	}

	private fun getLastLoggedUser() {
		setLoading()
		CoroutineScope(Dispatchers.IO).launch {
			when (val result = repository.get()) {
				is Repository.Result.Success	-> getLoginData(result.data)
				is Repository.Result.Failure	-> setToast(context.getString(R.string.connection_error_generic_message))
			}
		}
	}

	private fun onUsernameChanged(username: String) {
		buttonEnabled.set(username.isNotEmpty() && password.value?.isNotEmpty() ?: false)
	}

	private fun onPasswordChanged(password: String) {
		buttonEnabled.set(username.value?.isNotEmpty() ?: false && password.isNotEmpty())
	}

	private fun onLoginSuccess(response: Response<LoginResponseDTO>) {
		CoroutineScope(Dispatchers.Main).launch {
			setNotLoading()
			if (response.isSuccessful) {
				val	details		= response.body()
				val error		= details?.error
				val	account		= details?.account

				if (error?.code != null) {
					setToast("${error.code} - ${error.message}")
				}

				if (account?.userID != null) {
					setLastLoggedUser(details.account)
				}
			} else {
				setToast(context.getString(R.string.connection_error_generic_message))
			}
		}
	}

	private fun setLastLoggedUser(account: UserAccountDTO) {
		CoroutineScope(Dispatchers.IO).launch {
			when (repository.persist(Login(user = username.value!!))) {
				is Repository.Result.Success	-> setSuccessful(account)
				is Repository.Result.Failure	-> setToast("Erro ao efetuar login")
			}
		}
	}

	private fun getLoginData(login: Login?) {
		setNotLoading()
		CoroutineScope(Dispatchers.Main).launch {
			if (login != null) {
				username.value = login.user
			}
		}
	}

	private fun setSuccessful(account: UserAccountDTO) {
		CoroutineScope(Dispatchers.Main).launch {
			successful.value = account.getAccountEntity()
		}
	}

	private fun handleLoginError(error: Throwable) {
		setNotLoading()
		setToast(context.getString(R.string.connection_error_generic_message))
		error.printStackTrace()
	}
}