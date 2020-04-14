package pt.felipegouveia.bankapp.data.login.model

import pt.felipegouveia.bankapp.domain.model.common.Error
import pt.felipegouveia.bankapp.domain.model.login.Login
import pt.felipegouveia.bankapp.domain.model.login.UserAccount

/**
 * Map data entity from repository to domain entity and from domain entity
 * to data entity.
 */
class LoginMapper {

    /**
     * Map login from data entity to domain entity
     */
    fun mapLoginDataEntityToDomainEntity(response: LoginData): Login =
        Login(
            userAccount = mapUserAccountDataToDomain(response.userAccount),
            error = Error(response.error?.message)
        )

    private fun mapUserAccountDataToDomain(data: UserAccountData?): UserAccount =
        UserAccount(
            userId = data?.userId,
            agency = data?.agency,
            balance = data?.balance,
            bankAccount = data?.bankAccount,
            name = data?.name
        )
}