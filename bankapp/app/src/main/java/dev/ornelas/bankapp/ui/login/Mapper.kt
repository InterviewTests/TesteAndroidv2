package dev.ornelas.bankapp.ui.login

import dev.ornelas.banckapp.domain.model.Mapper
import dev.ornelas.banckapp.domain.model.User

object UserToUIMapper : Mapper<User, UserViewModel> {
    override fun map(type: User?): UserViewModel {
        return UserViewModel(
            id = type!!.id,
            bankAccount = type.bankAccount,
            agency = type.agency,
            balance = type.balance,
            name = type.name
        )
    }
}