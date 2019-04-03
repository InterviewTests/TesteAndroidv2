package br.com.rms.bankapp.data.repository.user

import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.User
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import br.com.rms.bankapp.data.remote.model.UserResponse
import br.com.rms.bankapp.utils.validations.user.UserValidations
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val accountDao: AccountDao,
    private val apiService: BankAppApiService,
    private val validation: UserValidations
) {

    lateinit var user : User
    lateinit var userResponse: UserResponse
    fun validateUserData(userLogin: String?, password: String?): Completable {
        user = User(0, userLogin!!, password!!,0)
        return validation(userLogin, password)
    }

    private fun saveUserData(it: UserResponse): Completable {
        user.accountId = it.userAccount?.userId
        return addUser(user).andThen(it.userAccount?.let { it1 -> addAccount(it1) })

    }
    fun validation(user: String?, password: String?): Completable {
        return Completable.fromCallable {
            validation.validateLoginData(user.toString(), password.toString())
        }
    }

    fun getRemoteUserData() : Completable  {
        return apiService.login("test_user", "Test@1").flatMapCompletable { userResponse -> saveUserData(userResponse) }
    }

    fun getLocalUserData() : Single<User>{
        return Single.fromCallable {
            userDao.selectUser()
        }
    }

    fun getLocalUserAccount() : Single<Account>{
        return Single.fromCallable {
            val user = userDao.selectUser()
            accountDao.selectAccount(user.accountId)
        }
    }


    fun addUser(user: User) : Completable = Completable.fromCallable{
        userDao.insertUser(user)}

    fun addAccount(account : Account) : Completable = Completable.fromCallable {
        accountDao.insertAccount(account)
    }

}