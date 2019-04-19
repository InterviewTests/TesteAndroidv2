package br.com.rms.bankapp.data.repository.user

import androidx.lifecycle.LiveData
import br.com.rms.bankapp.data.local.database.entity.Account
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepositoryContract {
    fun loadUserName(): LiveData<String>
    fun login(userLogin: String, userPassword: String): Completable
    fun getLocalUserAccount(): Single<Account>

}
