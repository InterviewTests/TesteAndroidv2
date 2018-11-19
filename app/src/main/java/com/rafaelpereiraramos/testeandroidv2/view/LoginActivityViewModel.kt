package com.rafaelpereiraramos.testeandroidv2.view

import androidx.lifecycle.*
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import com.rafaelpereiraramos.testeandroidv2.repo.ResourceWrapper
import com.rafaelpereiraramos.testeandroidv2.repo.UserRepo
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
class LoginActivityViewModel @Inject constructor(
    private val userRepo: UserRepo
) : ViewModel() {

    enum class Status {
        LOGGED,
        CREDENTIALS_NOT_FOUND
    }

    private val _status = MediatorLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    lateinit var user: UserTO

    fun login(userName: String, password: String) {
        val result = userRepo.getUser(userName, password)

        _status.addSource(result) { user ->
            if (user == null) {
                _status.value = Status.CREDENTIALS_NOT_FOUND

                return@addSource
            }

            this.user = user
            _status.value = Status.LOGGED
        }
    }
}