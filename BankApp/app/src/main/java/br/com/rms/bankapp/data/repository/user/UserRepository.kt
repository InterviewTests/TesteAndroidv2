package br.com.rms.bankapp.data.repository.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.USER_ID
import br.com.rms.bankapp.data.local.database.entity.User
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import br.com.rms.bankapp.data.remote.model.UserResponse
import br.com.rms.bankapp.utils.validations.LoginErrorException
import br.com.rms.bankapp.utils.validations.user.UserValidations
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val accountDao: AccountDao,
    private val apiService: BankAppApiService,
    private val validation: UserValidations
) : UserRepositoryContract {

    override fun getLocalUserAccount(accountId: Int): LiveData<Account> {
        return accountDao.selectAccount(accountId)
    }

    override fun getLocalUserAccountId(): LiveData<Int> {
        return userDao.selectUserAccountId()
    }

    override fun loadUserName(): LiveData<String> {
        return userDao.selectUserName()
    }

    override fun login(userLogin: String, password: String): Completable {
        return validateLoginInformation(userLogin, password)
            .andThen(getRemoteUserData(userLogin, password))
    }

    private fun validateLoginInformation(user: String?, password: String?): Completable {
        return Completable.fromCallable {
            validation.validateLoginData(user.toString(), password.toString())
        }
    }

    private fun getRemoteUserData(userLogin: String, password: String): Completable {
        return apiService.login(userLogin, password)
            .flatMapCompletable { userResponse -> saveUserData(userLogin, password, userResponse) }
    }

    private fun saveUserData(userLogin: String, password: String, userResponse: UserResponse): Completable {
        if (userResponse.error?.code == 0) {
            val account = userResponse.userAccount
            val user = getUser(userLogin, password, account)
            return insertUserInDatabase(user).andThen(account?.let { insertAccountInDatabase(it) })
        }
        return Completable.error(LoginErrorException(userResponse.error?.message))
    }



    private fun getUser(userLogin: String, password: String, account: Account?): User {
        val accountId = account?.userId
        return User(
            id = USER_ID,
            userLogin = userLogin,
            password = password,
            accountId = accountId
        )
    }

    private fun insertUserInDatabase(user: User): Completable = Completable.fromCallable {
        userDao.insertUser(user)
    }

    private fun insertAccountInDatabase(account: Account): Completable = Completable.fromCallable {
        accountDao.insertAccount(account)
    }

}