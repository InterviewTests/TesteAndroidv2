package fingerfire.com.extractbank.utils

object Validations {

    private const val UPPERCASE = "A senha precisa ter ao menos uma letra maiúscula"
    private const val NUMBER = "A senha precisa ter ao menos um número"
    private const val SPECIAL_CHAR = "A senha precisa ter ao menos um caractere especial"
    private const val PASSWORD_ERROR_REQUEST = "A senha precisa ter ao menos um caractere especial"
    private const val MINIMUM_PASSWORD_LENGTH = "A senha deve ter no mínimo 6 caracteres"
    private const val MINIMUM_PASSWORD = 6

    fun isValidCPF(cpf: String): Boolean {
        if (cpf.length != 11) {
            return false
        }

        if (cpf.all { it == cpf[0] }) {
            return false
        }

        var sum = 0
        for (i in 0 until 9) {
            sum += Character.getNumericValue(cpf[i]) * (10 - i)
        }
        var remainder = 11 - sum % 11
        if (remainder == 10 || remainder == 11) {
            remainder = 0
        }
        if (remainder != Character.getNumericValue(cpf[9])) {
            return false
        }

        sum = 0
        for (i in 0 until 10) {
            sum += Character.getNumericValue(cpf[i]) * (11 - i)
        }
        remainder = 11 - sum % 11
        if (remainder == 10 || remainder == 11) {
            remainder = 0
        }
        if (remainder != Character.getNumericValue(cpf[10])) {
            return false
        }

        return true
    }

    fun String.isValidEmail(): Boolean {
        val emailPattern = "^[A-Za-z](.*)(@)(.{1,})(\\.)(.{1,})"
        return matches(emailPattern.toRegex())
    }

    fun isPasswordValid(password: String): ValidationResult {
        if (password.length < MINIMUM_PASSWORD) {
            return ValidationResult(false, MINIMUM_PASSWORD_LENGTH)
        }

        val hasUppercase = "[A-Z]".toRegex().containsMatchIn(password)
        val hasNumber = "\\d".toRegex().containsMatchIn(password)
        val hasSpecialChar =
            "[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex().containsMatchIn(password)

        return if (hasUppercase && hasNumber && hasSpecialChar) {
            ValidationResult(true, null)
        } else {
            val errorMessage = when {
                !hasUppercase -> UPPERCASE
                !hasNumber -> NUMBER
                !hasSpecialChar -> SPECIAL_CHAR
                else -> PASSWORD_ERROR_REQUEST
            }
            ValidationResult(false, errorMessage)
        }
    }

    data class ValidationResult(val isValid: Boolean, val errorMessage: String? = "")

    }