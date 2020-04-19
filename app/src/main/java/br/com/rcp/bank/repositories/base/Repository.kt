package br.com.rcp.bank.repositories.base

import br.com.rcp.bank.Application
import br.com.rcp.bank.ServiceAPI
import br.com.rcp.bank.database.Database
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

abstract class Repository {
	@Inject
	protected	lateinit 	var		service		: ServiceAPI

	@Inject
	protected 	lateinit 	var		database	: Database

	init {
		initialize()
	}

	@Suppress("FunctionName")
	protected suspend fun <T> HandleResult(dispatcher: CoroutineDispatcher, call: suspend () -> T) : Result<T> {
		return withContext(dispatcher) {
			try {
				Result.Success(call.invoke())
			} catch (exception: Exception) {
				Result.Failure(exception)
			}
		}
	}

	private fun initialize() {
		Application.component.inject(this)
	}

	sealed class Result<out T> {
		data class Success<out T>	(val data: T)			: Result<T>()
		data class Failure			(val error: Throwable)	: Result<Nothing>()
	}
}