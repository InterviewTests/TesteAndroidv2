package com.example.bankapp.view.mainActivity

import android.content.Context
import com.example.bankapp.api.BankAPIInterface
import com.example.bankapp.api.BankAPIService
import com.example.bankapp.model.User
import com.example.bankapp.pojo.login.LoginPojo
import com.example.bankapp.util.SharedPrefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error

class MainPresenter(private val mView: MainContract.View): MainContract.Presenter{

    override fun doLogin(password: String, user: String) {
        SharedPrefs.getInstance(mView as Context).setLogin(user)
        val apiInterface = BankAPIService.getClient().create(BankAPIInterface::class.java)

        val callws = apiInterface.doLogin(password, user)

        callws.enqueue(object : Callback<LoginPojo> {
            override fun onResponse(call: Call<LoginPojo>?, response: Response<LoginPojo>?) {
                val resource = response?.body()
                if (resource != null) {
                    if (resource.error?.code != null && resource.error!!.code!! > 0){
                        SharedPrefs.getInstance(mView as Context).logout()
                        mView.showMessage(resource.error?.message!!)
                    } else {
                        val user = User(
                            userId = resource.userAccount?.userId,
                            name = resource.userAccount?.name,
                            bankAccount = resource.userAccount?.bankAccount,
                            agency = resource.userAccount?.agency,
                            balance = resource.userAccount?.balance
                        )
                        mView.logged(user)
                    }

                }
            }

            override fun onFailure(call: Call<LoginPojo>?, t: Throwable?) {
                SharedPrefs.getInstance(mView as Context).logout()
            }
        })
    }

    override fun getLogged(): String? {
        return SharedPrefs.getInstance(mView as Context).getLogin()
    }
}