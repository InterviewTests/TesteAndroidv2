package projects.kevin.bankapp.user.detail

import projects.kevin.bankapp.user.service.APIClient
import projects.kevin.bankapp.base.BasePresenter
import projects.kevin.bankapp.user.login.UserAccount
import projects.kevin.bankapp.user.sharedPref.UserDataSharedPref
import java.math.BigDecimal

class DetailPresenter(private val view: DetailView): BasePresenter() {

    fun loadUserData(userPreferences: UserDataSharedPref) {
        view.loadUserAccountPreferences (
            UserAccount(userId = userPreferences.getUserId()?.toLong()!!,
                name = userPreferences.getName()!!,
                bankAccount = userPreferences.getBankAccount()!!,
                agency = userPreferences.getAgency()!!,
                balance = BigDecimal(userPreferences.getBalance()!!)
            )
        )
    }

    fun fetchUserStatements(userId: Long) {
        view.showLoading()
        APIClient.getReactiveService()?.let { apiUserService ->
            execute(apiUserService.fetchUserDetail(userId)).subscribe( { detailApiResponse->
                view.loadStatements(detailApiResponse.statementList)
            }, { throwable: Throwable? ->
                view.hideLoading()
            })
        }
    }

}