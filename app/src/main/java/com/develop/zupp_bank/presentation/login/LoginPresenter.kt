package com.develop.zupp_bank.presentation.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.develop.zupp_bank.domain.interfaces.UserRepository
import com.develop.zupp_bank.domain.models.UserAccount
import com.develop.zupp_bank.framework.Tools.isCPF
import com.develop.zupp_bank.framework.Tools.isEmailValid
import com.develop.zupp_bank.framework.Tools.isPasswordValidate
import com.develop.zupp_bank.infrastructure.repositories.disk.SPUtils
import com.develop.zupp_bank.infrastructure.utils.Resource
import com.develop.zupp_bank.infrastructure.utils.ResourceCallback
import com.develop.zupp_bank.presentation.main.ZupApplication
import javax.inject.Inject

class LoginPresenter(var view: LoginView): LoginContract.Presenter {

    @Inject
    internal lateinit var userRepository: UserRepository

    @Inject
    internal lateinit var context: Context

    @Inject
    lateinit var spUtils: SPUtils

    var validation: MutableLiveData<Resource<UserAccount>>
    var usuario: String = ""
    var password: String = ""
    private var registrationData: RegistrationData


    init {

        ZupApplication.instance.componentApplication.inject(this)

        registrationData = ViewModelProviders.of(view.getActivityView()).get(RegistrationData::class.java)

        validation = MutableLiveData()
        if (!validation.hasObservers()) {
            validation.observe(view.getLifeCycleOwnerView(), Observer {
                when {
                    it.status === Resource.Status.LOADING -> {
                        //view.showWait()
                    }

                    it.status === Resource.Status.SUCCESS -> {

                        if (it.userAccount!!.name != null){

                            registrationData.userId = it.userAccount!!.userId
                            registrationData.name = it.userAccount!!.name
                            registrationData.bankAccount = it.userAccount!!.bankAccount
                            registrationData.agency = it.userAccount!!.agency
                            registrationData.balance = it.userAccount!!.balance
                            view.navigate()
                            //view.showMessage(it.userAccount!!.name)

                        }else{
                            view.showMessage("Usuário ou senha incorretos!")
                        }

                    }

                    it.status === Resource.Status.MESSAGE -> {
                        //view.hideWait()

                    }

                    it.status === Resource.Status.FAILURE -> {
                        view.showMessage("Problemas na comunicação com o servidor, tente novamente!")
                    }

                    it.status === Resource.Status.ERROR -> {
                        //view.hideWait()
                        //view.showPopupFailure()
                        view.showMessage("Problemas na comunicação com o servidor, tente novamente!")
                    }
                }
            })
        }

        //checkLogin()

    }

    override fun processLogin(user: String, pass: String) {

        if (isEmailValid(user) or isCPF(user)){

            if (isPasswordValidate(pass)){
                callLogin(user,pass)
            }
            else{
                view.showMessage("A senha deve conter, uma letra minúscula, uma letra maiúscula, um caracter numérico e um caracter especial!")
                return
            }

        }
        else{
            view.showMessage("Usúario informado não é um email ou um CPF!")
            return
        }

    }


    private fun callLogin(user: String, pass: String) {
        usuario = user
        password = pass
        userRepository.login(user, pass, object : ResourceCallback<Resource<UserAccount>>{
            override fun onComplete(t: Resource<UserAccount>) {
                validation.postValue(t)
            }
        })
    }

    override fun processMsg(msg: String) {
        view.showMessage(msg)
    }

    override fun checkLogin(): String {
        return spUtils.isLogged()
    }

    override fun checkPass(): String {
        return spUtils.isLoggedPass()
    }

}