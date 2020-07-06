package br.com.mdr.testeandroid.model.mapper

import br.com.mdr.testeandroid.model.api.ErrorWrapperApiModel
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.ErrorList
import br.com.mdr.testeandroid.model.business.ErrorType

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */

fun ErrorWrapperApiModel.toBusinessModel(): ErrorList {
    return errors?.let { errors ->
        ErrorList(
            hasError = !this.errors.isNullOrEmpty(),
            list = errors.map {
                Error(
                    messageId = it.message.orEmpty(),
                    param = it.param,
                    errorType = ErrorType.fromServerErrorCode(it.errorCode ?: 1)
                )
            }
        )
    } ?: kotlin.run {
        ErrorList.unknown()
    }
}