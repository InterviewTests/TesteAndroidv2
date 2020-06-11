package projects.kevin.bankapp.user.login

import projects.kevin.bankapp.user.service.APIClient
import projects.kevin.bankapp.base.BasePresenter
import projects.kevin.bankapp.user.sharedPref.UserDataSharedPref

class LoginPresenter(private val view: LoginView): BasePresenter() {

    fun userLogin(credentials: LoginApiRequest) {
        view.showLoading()
        APIClient.getReactiveService()?.let { apiUserService ->
            execute(apiUserService.login(credentials)).subscribe( { loginResponse ->
                if(loginResponse?.error?.code != null) {
                    view.onLoginFailed()
                } else {
                    view.onLoginSuccess(loginResponse.userAccount)
                }
            }, { throwable: Throwable? ->
                view.onRequestFailed()
                view.hideLoading()
            })
        }
    }

    fun saveOnShared(userData: UserAccount, userPreferences: UserDataSharedPref) {
        with(userData) {
            userPreferences.persistAgency(agency)
                    userPreferences.persistBalance(balance.toString())
                    userPreferences.persistName(name)
                    userPreferences.persistBankAccount(bankAccount)
                    userPreferences.persistUserId(userId.toString())
        }
        view.hideLoading()
        view.onSaveUserData()

    }

}