package projects.kevin.bankapp.user.login

interface LoginView {

    fun onLoginSuccess(userAccount: UserAccount)
    fun onLoginFailed()
    fun onSaveUserData()
    fun showLoading()
    fun hideLoading()
    fun onRequestFailed()
}