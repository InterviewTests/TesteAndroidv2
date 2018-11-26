package com.rafaelpereiraramos.testeandroidv2.view.login

import androidx.lifecycle.*
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import com.rafaelpereiraramos.testeandroidv2.repo.ResourceWrapper.Status.*
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

    private var isLogin = false

    private val _status = MediatorLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    lateinit var user: UserTO

    fun login(userName: String, password: String) {
        if (isLogin)
            return

        isLogin = true

        val result = userRepo.getUser(userName, password)

        _status.addSource(result) { resource ->

            when(resource.status) {
                SUCCESS -> {
                    user = resource.data!!
                    _status.value =
                            Status.LOGGED
                }
                // TODO threat each different error
                ERROR -> {
                    _status.value =
                            Status.CREDENTIALS_NOT_FOUND
                    isLogin = false
                }

                LOADING -> {} //ignore
            }
        }
    }
}