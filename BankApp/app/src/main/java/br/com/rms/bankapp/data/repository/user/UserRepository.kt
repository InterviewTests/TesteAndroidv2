package br.com.rms.bankapp.data.repository.user

import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import br.com.rms.bankapp.utils.validations.user.UserValidations
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val accountDao: AccountDao,
    private val apiService: BankAppApiService,
    private val validation: UserValidations
) {


    fun login(user: String?, password: String?): Completable {

        return validation(user,password)
    }

    fun validation(user: String?, password: String?) : Completable {
        return Completable.fromCallable{
            validation.validateLoginData(user.toString(), password.toString())
        }
    }
}