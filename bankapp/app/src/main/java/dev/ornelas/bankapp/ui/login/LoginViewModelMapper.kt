package dev.ornelas.bankapp.ui.login

import dev.ornelas.banckapp.domain.model.Mapper
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.LoginResponseApi
import dev.ornelas.bankapp.data.repository.ApiToUserModel

object UserToUIMapper : Mapper<User, LoggedInUserView> {
    override fun map(type: User?): LoggedInUserView {
        return LoggedInUserView(
            id = type!!.id,
            bankAccount = type.bankAccount,
            agency = type.agency,
            balance = type.balance,
            name = type.name
        )
    }
}