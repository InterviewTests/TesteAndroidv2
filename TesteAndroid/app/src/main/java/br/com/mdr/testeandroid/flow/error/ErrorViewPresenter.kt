package br.com.mdr.testeandroid.flow.error

import androidx.annotation.StringRes
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.ErrorType

class ErrorViewPresenter(
    @StringRes
    override val messageId: Int? = null,
    override val messageIdName: String = "",
    override val param: String? = "",
    private val defaultMessageId: Int = R.string.message_resource_internal_error
) : IErrorViewPresenter {

    override fun withError(error: Error): IErrorViewPresenter {
        return ErrorViewPresenter(
            param = error.param,
            messageIdName = error.messageId,
            defaultMessageId = defaultMessageFromType(error.errorType)
        )
    }

    override fun messageId(): Int {
        return messageId ?: defaultMessageId
    }

    @StringRes
    private fun defaultMessageFromType(errorType: ErrorType): Int {
        return when (errorType) {
            ErrorType.REQUIRED_FIELD -> R.string.message_resource_required_field
            else -> R.string.message_resource_internal_error
        }
    }
}
