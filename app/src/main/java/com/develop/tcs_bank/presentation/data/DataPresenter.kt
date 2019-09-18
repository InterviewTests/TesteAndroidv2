package com.develop.tcs_bank.presentation.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.develop.tcs_bank.domain.interfaces.UserRepository
import com.develop.tcs_bank.domain.models.StatementList
import com.develop.tcs_bank.framework.Tools.formatForBrazilianCurrency
import com.develop.tcs_bank.infrastructure.repositories.disk.SPUtils
import com.develop.tcs_bank.infrastructure.utils.Resource
import com.develop.tcs_bank.infrastructure.utils.ResourceCallback
import com.develop.tcs_bank.presentation.login.RegistrationData
import com.develop.tcs_bank.presentation.main.TcsApplication
import javax.inject.Inject

class DataPresenter(var view: DataView): DataContract.Presenter {


    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var spUtils: SPUtils
    var registrationData: RegistrationData
    private var resourceLiveData: MutableLiveData<Resource<List<StatementList>>>

    init {
        TcsApplication.instance.componentApplication.inject(this)
        registrationData = ViewModelProviders.of(view.getActivityView()).get(RegistrationData::class.java)

        resourceLiveData = MutableLiveData()

        if (!resourceLiveData.hasObservers()) {
            resourceLiveData.observe(view.getLifeCycleOwnerView(), Observer {
                when {
                    it.status === Resource.Status.SUCCESS -> {
                        view.configureList(it.userAccount!!)
                    }

                    it.status === Resource.Status.FAILURE -> {
                       // view.showPopupFailure()
                        view.showMessage("Falha na comunicação com o servidor!")
                    }

                    it.status === Resource.Status.ERROR -> {
                       // view.showPopupFailure()
                    }
                }
            })
        }


    }

    override fun loadList() {
        userRepository.getStatement(registrationData.userId, object : ResourceCallback<Resource<List<StatementList>>> {
            override fun onComplete(t: Resource<List<StatementList>>) {
                resourceLiveData.postValue(t)
            }
        })
    }

    override fun setName(): String{
        return registrationData.name
    }

    override fun setConta(): Int{
        return registrationData.bankAccount
    }

    override fun setBalance(): String{
        return formatForBrazilianCurrency(registrationData.balance)
    }

    override fun processLogout() {
        spUtils.logoff()
        view.navigate()
    }

    override fun setAgency(): String {
        return registrationData.agency
    }

}