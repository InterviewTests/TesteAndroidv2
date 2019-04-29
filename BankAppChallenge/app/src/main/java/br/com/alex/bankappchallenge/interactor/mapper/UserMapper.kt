package br.com.alex.bankappchallenge.interactor.mapper

import br.com.alex.bankappchallenge.extensions.asBRL
import br.com.alex.bankappchallenge.model.FormatedUserAccount
import br.com.alex.bankappchallenge.network.model.UserAccount
import br.com.alex.bankappchallenge.utils.BankAccountFormatterContract

interface UserMapperContract {
    fun mapper(userAccount: UserAccount): FormatedUserAccount
}

class UserMapper(
    private val bankAccountFormatterContract: BankAccountFormatterContract
) : UserMapperContract {
    override fun mapper(userAccount: UserAccount) =
        userAccount.let {
            FormatedUserAccount(
                it.name,
                bankAccountFormatterContract.formatter(it.bankAccount, it.agency),
                it.balance.asBRL()
            )
        }
}