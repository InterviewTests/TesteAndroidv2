package br.com.rcp.bank.repositories

import br.com.rcp.bank.database.models.Login
import br.com.rcp.bank.dto.LoginResponseDTO
import br.com.rcp.bank.repositories.base.Repository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class LoginRepository: Repository() {
	suspend fun login(username: String, password: String) : Result<Response<LoginResponseDTO>> {
		return HandleResult(Dispatchers.IO) { service.doLogin(username, password) }
	}

	suspend fun persist(login: Login): Result<Unit> {
		return HandleResult(Dispatchers.IO) { database.loginDAO().save(login) }
	}

	suspend fun get(): Result<Login?> {
		return HandleResult(Dispatchers.IO) { database.loginDAO().getLogin() }
	}
}