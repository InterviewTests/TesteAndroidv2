package com.accenture.santander.login

import android.app.Activity
import com.accenture.santander.viewmodel.User
import com.accenture.santander.interector.dataManager.storag.StoragManager
import com.accenture.santander.interector.dataManager.entity.UserEntity
import com.accenture.santander.interector.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.interector.dataManager.storag.IStoragManager
import com.accenture.santander.entity.UserAccount
import com.accenture.santander.interector.remote.service.login.IServiceLogin
import com.accenture.santander.interector.remote.service.IConnect
import javax.inject.Inject


class LoginInteractor(
    activity: Activity,
    private val iLoginInteractorOutput: LoginContracts.LoginInteractorOutput,
    private val iServiceLogin: IServiceLogin
) : LoginContracts.LoginInteractorInput {

    @Inject
    lateinit var iConnect: IConnect

    @Inject
    lateinit var iUserRepository: IUserRepository

    @Inject
    lateinit var iStoragManager: IStoragManager

    init {
        DaggerLoginComponents
            .builder()
            .loginModule(LoginModule(activity))
            .build()
            .inject(this)
    }

    override fun login(user: User) {
        if (iConnect.verifyConnection()) {
            iServiceLogin.login(user = user,
                success = {
                    if (it.code() == 200) {
                        val auth = it.body()
                        if (auth == null) {
                            iLoginInteractorOutput.failNetWork()
                        } else {
                            if (auth.userAccount.userId > 0) {
                                registerUser(auth = auth.userAccount, user = user)
                                iLoginInteractorOutput.sucessLogin()
                            } else {
                                iLoginInteractorOutput.errorMensage(auth.error.message)
                            }
                        }
                    } else {
                        iLoginInteractorOutput.failResquest(it.code())
                    }
                },
                failure = {
                    iLoginInteractorOutput.errorLogin(it)
                })
        } else {
            iLoginInteractorOutput.failNetWork()
        }
    }

    override fun registerUser(auth: UserAccount, user: User) {
        val userEntity = iUserRepository.findDesc()

        if (userEntity != null) {
            iUserRepository.update(userEntity.mapper(auth))
        } else {
            iUserRepository.insert(
                UserEntity().mapper(auth)
            )
        }

        iStoragManager.setPreferences(StoragManager.LOGIN, user.login)

        iLoginInteractorOutput.startLogged()
    }

    override fun searchData() {
        val login = iStoragManager.getPreferences(StoragManager.LOGIN)
        iLoginInteractorOutput.resultData(login, "")
    }
}