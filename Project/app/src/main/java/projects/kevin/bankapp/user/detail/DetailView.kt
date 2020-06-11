package projects.kevin.bankapp.user.detail

import projects.kevin.bankapp.user.login.UserAccount

interface DetailView {

    fun loadUserAccountPreferences(userAccount: UserAccount)
    fun loadStatements(bankStatements: ArrayList<BankStatements>?)
    fun showLoading()
    fun hideLoading()
}