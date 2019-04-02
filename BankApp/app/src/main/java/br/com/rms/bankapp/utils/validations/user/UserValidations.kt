package br.com.rms.bankapp.utils.validations.user

import br.com.rms.bankapp.R
import br.com.rms.bankapp.utils.validations.EditTextValidationException
import br.com.rms.bankapp.utils.validations.MultipleValidationExceptions
import br.com.rms.bankapp.utils.validations.ValidationException
import javax.inject.Inject

class UserValidations @Inject constructor() {

    private val userNameRegexNumber = "^[1-9]\\d*\$".toRegex()
    private val userNameRegexEmail =
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex()
    private val userPasswordRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){5,8}\$".toRegex()
    private val isNumber = "^[0-9]*\$".toRegex()

    fun validateLoginData(user: String, password: String) {
        val validationExceptions = getLoginValidationException(user, password)
        throwIfNotEmpty(validationExceptions)
    }

    private fun getLoginValidationException(user: String?, password: String?): MutableList<ValidationException> {
        val validationExceptions = mutableListOf<ValidationException>()

        if (user.isNullOrEmpty() || isNumber.matches(user)) {

            val cpfValidator = CpfValidator()

            if (user.isNullOrBlank() || !cpfValidator.isCPF(user)) {
                val validationException = EditTextValidationException(R.id.tfiUser, R.string.user_documento_invalid)
                validationExceptions.add(validationException)
            }

        } else {

            if (user.isNullOrBlank() || !userNameRegexEmail.matches(user)) {
                val validationException = EditTextValidationException(R.id.tfiUser, R.string.user_email_invalid)
                validationExceptions.add(validationException)
            }

        }

        if (password.isNullOrBlank() || !userPasswordRegex.matches(password)) {
            val validationException = EditTextValidationException(R.id.tfiPassword, R.string.user_password_invalid)
            validationExceptions.add(validationException)
        }
        return validationExceptions
    }

    private fun throwIfNotEmpty(validationExceptions: MutableList<ValidationException>) {
        if (validationExceptions.isNotEmpty()) throw MultipleValidationExceptions(validationExceptions)
    }

}