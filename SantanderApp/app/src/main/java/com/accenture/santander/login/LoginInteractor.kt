package com.accenture.santander.login

import android.content.Context
import com.accenture.santander.viewmodel.User
import com.accenture.santander.dataManager.Storag.StoragManager
import com.accenture.santander.dataManager.entity.UserEntity
import com.accenture.santander.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.dataManager.repository.deviceRepository.UserRepository
import com.accenture.santander.entity.UserAccount
import com.accenture.santander.remote.service.login.IServiceLogin
import com.accenture.santander.remote.service.login.ServiceLogin
import com.accenture.santander.utils.Connect

import org.junit.Assert.*
import org.junit.Test

class LoginInteractor(
    private val context: Context,
    private val iLoginInteractorOutput: LoginContracts.LoginInteractorOutput
) : LoginContracts.LoginInteractorInput {

    private val iUserRepository: IUserRepository = UserRepository(context)
    private val storagManager: StoragManager = StoragManager(context)
    private val iServiceLogin: IServiceLogin = ServiceLogin()

    @Test
    override fun login(user: User) {

        assert(Connect.verifyConnection(context))

        if (Connect.verifyConnection(context)) {
            iServiceLogin.login(user = user,
                success = {
                    assert(it.code() == 200)

                    if (it.code() == 200) {
                        assertNotNull(it.body())
                        iLoginInteractorOutput.sucessLogin(auth = it.body(), user = user)
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

    @Test
    override fun registerUser(auth: UserAccount, user: User) {
        val userEntity = iUserRepository.findViewById(1)

        if (userEntity != null) {
            userEntity.iduser = auth.userId
            userEntity.name = auth.name
            userEntity.bankAccount = auth.bankAccount
            userEntity.agency = auth.agency
            userEntity.balance = auth.balance
            iUserRepository.update(userEntity)
        } else {
            iUserRepository.insert(
                UserEntity(
                    id = 1,
                    iduser = auth.userId,
                    name = auth.name,
                    bankAccount = auth.bankAccount,
                    agency = auth.agency,
                    balance = auth.balance
                )
            )
        }

        storagManager.setPreferences(StoragManager.LOGIN, user.login)
        assertNotNull(storagManager.getPreferences(StoragManager.LOGIN))
        assert(!storagManager.getPreferences(StoragManager.LOGIN).equals(""))

        iLoginInteractorOutput.startLogged()
    }

    override fun searchData() {
        val login = storagManager.getPreferences(StoragManager.LOGIN)
        assert(!login.equals(""))

        iLoginInteractorOutput.resultData(login, "")
    }
}