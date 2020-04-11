package pt.felipegouveia.bankapp.presentation.login.entity.mapper

import pt.felipegouveia.bankapp.domain.common.Mapper
import pt.felipegouveia.bankapp.domain.model.login.Login
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.login.entity.LoginPresentation

/**
 * Map the domain entity retrieved from (data/domain) layer to presentation entity.
 */
class LoginPresentationMapper : Mapper<Login, LoginPresentation>() {

    override fun mapFrom(data: Login): LoginPresentation =
        mapLoginToPresentation(data)

    private fun mapLoginToPresentation(domain: Login): LoginPresentation =
        LoginPresentation(
            userAccount = domain.userAccount,
            error = Error(domain.error?.message, domain.error?.stringId)
        )
}