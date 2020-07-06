package br.com.mdr.testeandroid.model.mapper

import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User

/**
 * @author Marlon D. Rocha
 * @since 04/07/2020
 */
fun UserApiModel.toBusinessModel(): User {
    return User(
        userId = userAccount?.userId,
        name = userAccount?.name.orEmpty(),
        bankAccount = userAccount?.bankAccount.orEmpty(),
        agency = userAccount?.agency.orEmpty(),
        balance = userAccount?.balance
    )
}

fun User.toApiModel(): UserApiModel {
    return UserApiModel(
        User(userId = userId,
        name = name.orEmpty(),
        bankAccount = bankAccount.orEmpty(),
        agency = agency.orEmpty(),
        balance = balance)
    )
}