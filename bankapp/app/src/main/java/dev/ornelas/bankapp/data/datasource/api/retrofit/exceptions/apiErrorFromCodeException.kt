package dev.ornelas.bankapp.data.datasource.api.retrofit.exceptions

import dev.ornelas.bankapp.data.datasource.api.retrofit.model.ErrorApi


fun userApiErrorFromCodeException(erro: ErrorApi): Exception {
    return if (erro.code == 53) {
        InvalidUserNamePasswordException(erro.message)
    } else {
        GenericApiException(erro.message)
    }
}


fun statementApiErrorFromCodeException(erro: ErrorApi): Exception {
    return if (erro.code == 53) {
        UserNotFoundException(erro.message)
    } else {
        GenericApiException(erro.message)
    }
}