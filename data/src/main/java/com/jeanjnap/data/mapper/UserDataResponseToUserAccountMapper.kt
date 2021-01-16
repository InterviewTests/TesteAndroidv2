package com.jeanjnap.data.mapper

import com.jeanjnap.data.model.response.UserDataResponse
import com.jeanjnap.domain.entity.UserAccount

class UserDataResponseToUserAccountMapper: Mapper<UserDataResponse, UserAccount> {
    override fun transform(item: UserDataResponse?) = UserAccount(
        userId = item?.userAccount?.userId,
        name = item?.userAccount?.name,
        bankAccount = item?.userAccount?.bankAccount,
        agency = item?.userAccount?.agency,
        balance = item?.userAccount?.balance
    )
}
