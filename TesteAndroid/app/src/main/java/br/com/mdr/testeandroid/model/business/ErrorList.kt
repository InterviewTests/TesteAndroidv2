package br.com.mdr.testeandroid.model.business

data class ErrorList(
    val hasError: Boolean,
    val list: List<Error>
) {

    companion object {
        fun unknown(): ErrorList {
            return ErrorList(list = listOf(Error(errorType = ErrorType.UNKNOWN)), hasError = true)
        }
    }
}

