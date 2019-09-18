package com.develop.tcs_bank.domain.interfaces

import com.develop.tcs_bank.domain.models.StatementList
import com.develop.tcs_bank.domain.models.UserAccount
import com.develop.tcs_bank.infrastructure.utils.Resource
import com.develop.tcs_bank.infrastructure.utils.ResourceCallback

interface UserRepository {

    fun login(user: String, pass: String, callback: ResourceCallback<Resource<UserAccount>>)
    fun getStatement(idUser: Int, callback: ResourceCallback<Resource<List<StatementList>>>)

}