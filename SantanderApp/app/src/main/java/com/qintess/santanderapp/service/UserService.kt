package com.qintess.santanderapp.service

import com.qintess.santanderapp.model.CredentialsModel
import com.qintess.santanderapp.model.UserModel

open class UserService: ServiceInterface {

    fun login(credentials: CredentialsModel, onSuccess: SuccessCallback<UserModel>, onFailure: FailureCallback) {
        val bodyParameters = RequestParameters()
        bodyParameters["user"] = credentials.user
        bodyParameters["password"] = credentials.password

        getHttpClient().post(
            "login", bodyParameters,
            onSuccess = { responseJsonObj ->
                val userJsonObj = responseJsonObj.getJSONObject("userAccount")
                val user = UserModel(
                    userId = userJsonObj.getInt("userId"),
                    name = userJsonObj.getString("name"),
                    bankAccount = userJsonObj.getString("bankAccount"),
                    agency = userJsonObj.getString("agency"),
                    balance = userJsonObj.getDouble("balance")
                )
                onSuccess(user)
            },
            onFailure = onFailure
        )
    }

    override fun getHttpClient(): HttpInterface {
        return Http()
    }
}