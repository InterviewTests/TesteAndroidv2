package br.com.bank.android.home.presentation.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.home.business.HomeBusiness
import br.com.bank.android.home.presentation.handler.HomeHandler
import br.com.bank.android.login.data.UserAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeBusiness: HomeBusiness,
    private val homeHandler: HomeHandler
) : ViewModel() {

    val userAccount = ObservableField<UserAccount>()

    fun logout() {
        homeHandler.onDisconnected()
    }

    fun loadStataments() = GlobalScope.launch(Dispatchers.Main) {
        userAccount.get()?.let {
            try {
                homeHandler.setLoading(true)
                val stataments = homeBusiness.getStataments(it.userId)

                homeHandler.onStataments(stataments)
            } catch (error: BusinessError) {
                homeHandler.onError(error)
            } finally {
                homeHandler.setLoading(false)
            }
        }
    }
}