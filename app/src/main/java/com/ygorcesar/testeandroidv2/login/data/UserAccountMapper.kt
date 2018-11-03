package com.ygorcesar.testeandroidv2.login.data

import com.ygorcesar.testeandroidv2.base.common.exception.EssentialParamMissingException
import com.ygorcesar.testeandroidv2.base.data.BaseMapper
import com.ygorcesar.testeandroidv2.login.model.UserAccount
import timber.log.Timber
import javax.inject.Inject

class UserAccountMapper @Inject constructor() :
    BaseMapper<UserAccountResponse, UserAccount>() {
    override val trackException: (Throwable) -> Unit = { exception ->
        Timber.i("Send error to Crashlytics $exception")
    }

    override fun assertEssentialParams(raw: UserAccountResponse) {
        val userAccountRaw =
            raw.data ?: throw EssentialParamMissingException("User account is null", raw)

        if (userAccountRaw.userId == null) addMissingParam("userId")
        if (userAccountRaw.name.isNullOrBlank()) addMissingParam("name")
        if (userAccountRaw.bankAccount.isNullOrBlank()) addMissingParam("bankAccount")
        if (userAccountRaw.agency.isNullOrBlank()) addMissingParam("agency")
        if (userAccountRaw.balance == null) addMissingParam("ballance")

        if (isMissingParams()) {
            throwException(userAccountRaw)
        }
    }

    override fun copyValues(raw: UserAccountResponse): UserAccount {
        val userAccountRaw = raw.data!!
        return UserAccount(
            userId = userAccountRaw.userId!!,
            name = userAccountRaw.name!!,
            bankAccount = userAccountRaw.bankAccount!!,
            agency = userAccountRaw.agency!!,
            balance = userAccountRaw.balance!!
        )
    }
}