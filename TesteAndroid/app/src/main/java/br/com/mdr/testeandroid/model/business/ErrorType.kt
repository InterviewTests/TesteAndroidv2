package br.com.mdr.testeandroid.model.business

enum class ErrorType {
    REQUIRED_FIELD(),
    NOT_FOUND,
    INTERNAL_ERROR,
    UNKNOWN;

    companion object {
        private const val ERROR_CODE_REQUIRED_FIELD = 1

        fun fromServerErrorCode(errorCode: Int): ErrorType {
            return when (errorCode) {
                ERROR_CODE_REQUIRED_FIELD -> REQUIRED_FIELD
                else -> INTERNAL_ERROR
            }
        }
    }
}
