package com.develop.zupp_bank.infrastructure.repositories.impl

import android.content.Context
import com.develop.zupp_bank.domain.interfaces.UserRepository
import com.develop.zupp_bank.domain.models.UserAccount
import com.develop.zupp_bank.domain.models.User
import com.develop.zupp_bank.infrastructure.repositories.disk.SPUtils
import com.develop.zupp_bank.infrastructure.repositories.remote.IApiServiceUser
import com.develop.zupp_bank.infrastructure.utils.NetworkBoundApiResource
import com.develop.zupp_bank.infrastructure.utils.Resource
import com.develop.zupp_bank.infrastructure.utils.ResourceCallback
import com.develop.zupp_bank.infrastructure.utils.ReturnAPI
import retrofit2.Call
import javax.inject.Inject

class UserRepositoryImpl
@Inject
constructor(var service: IApiServiceUser, private val context: Context,private val spUtils: SPUtils): UserRepository {

    override fun login(user: String, pass: String, callback: ResourceCallback<Resource<UserAccount>>) {

        object : NetworkBoundApiResource<UserAccount>(callback) {
            override fun handleDataSuccess(resultType: UserAccount): UserAccount {
                spUtils.login(user,pass)
                return resultType
            }

            override fun createCall(): Call<ReturnAPI<UserAccount>> {
                return service.login(User(user,pass))
            }
        }
    }


}