package fingerfire.com.extractbank.features.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.extractbank.features.login.data.LoginRepository
import fingerfire.com.extractbank.features.login.ui.viewstate.LoginViewState
import fingerfire.com.extractbank.model.Login
import fingerfire.com.extractbank.network.ServiceState
import fingerfire.com.extractbank.utils.Validations
import fingerfire.com.extractbank.utils.Validations.isValidEmail
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val loginMutableLiveData: MutableLiveData<LoginViewState> = MutableLiveData()
    private val savedUserMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private val defaultErrorMessage = "Ocorreu um erro durante o login. Tente novamente mais tarde."

    val loginLiveData: LiveData<LoginViewState> get() = loginMutableLiveData
    val getSavedUserLiveData: LiveData<String> get() = savedUserMutableLiveData

    init {
        getUserSaved()
    }

    private fun getUserSaved() {
        savedUserMutableLiveData.value = loginRepository.getSavedUser()
    }

    fun loginUser(login: Login) {
        val validationResult = validateLogin(login)
        if (validationResult.isValid) {
            performLogin(login)
        } else {
            handleValidationError(validationResult.errorMessage)
        }
    }

    private fun validateLogin(login: Login): ValidationResult {
        val passwordValidationResult = isPasswordValid(login.password)
        if (!passwordValidationResult.isValid) {
            return ValidationResult(false, passwordValidationResult.errorMessage)
        }

        return ValidationResult(true, null)
    }

    fun isValidEmailOrCPF(emailCpf: String): Boolean {
        val cleanedCpf = emailCpf.replace("[.\\-]".toRegex(), "")
        return emailCpf.isValidEmail() || Validations.isValidCPF(cleanedCpf)
    }

    fun isPasswordValid(password: String): ValidationResult {
        val validationResult = Validations.isPasswordValid(password)
        return ValidationResult(validationResult.isValid, validationResult.errorMessage)
    }

    private fun performLogin(login: Login) {
        viewModelScope.launch {
            when (val loginResponse = loginRepository.login(login)) {
                is ServiceState.Success -> {
                    loginMutableLiveData.postValue(
                        loginResponse.data?.let { LoginViewState.Success(it) }
                    )
                }

                is ServiceState.Error -> {
                    handleValidationError(defaultErrorMessage)
                }
            }
        }
    }

    private fun handleValidationError(errorMessage: String?) {
        loginMutableLiveData.postValue(LoginViewState.Error(errorMessage ?: defaultErrorMessage))
    }
}

data class ValidationResult(val isValid: Boolean, val errorMessage: String?)