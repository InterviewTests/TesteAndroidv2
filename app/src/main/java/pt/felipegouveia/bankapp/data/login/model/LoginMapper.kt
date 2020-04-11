package pt.felipegouveia.bankapp.data.login.model

import pt.felipegouveia.bankapp.domain.model.common.Error
import pt.felipegouveia.bankapp.domain.model.login.Login

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
            userAccount = response.userAccount,
            error = Error(response.error?.message)
        )
}