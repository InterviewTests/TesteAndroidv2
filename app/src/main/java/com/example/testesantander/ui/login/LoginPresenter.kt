package com.example.testesantander.ui.login

import com.example.testesantander.R
import com.example.testesantander.domain.model.UserData
import com.example.testesantander.domain.model.UserResponse
import com.example.testesantander.domain.usecase.IGetUserUseCase
import com.example.testesantander.mvp.BasePresenter
import com.example.testesantander.utils.CPFUtil
import com.example.testesantander.utils.EmailUtil
import com.example.testesantander.utils.PasswordUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter(private val getUserUseCase: IGetUserUseCase): BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    private lateinit var mUserResponse: UserResponse
    private lateinit var mUserData: UserData
    private lateinit var mUser : String
    private lateinit var mPassword : String


    override fun getUserCase(){
        getUserUseCase.execute(mUser, mPassword).enqueue(object : Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                mView?.onLoading(false)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                mUserResponse = response.body()!!
                mUserData = mUserResponse.userAccount
                mView?.getAccountData(mUserData)
                mView?.callStatements()
                mView?.saveLogin()
            }
        })
    }

    override fun checkValues(user: String, password: String){
        if (user.isEmpty()) {
            mView?.showToast(R.string.invalid_user)
        } else {
            mView?.onLoading(true)
            if (CPFUtil.myValidateCPF(user) || EmailUtil.isValidEmail(user)) {
                if (PasswordUtil.isValidPassword(password)) {
                    mUser = user
                    mPassword = password
                    mView?.login()
                }
                else{
                    mView?.showToast(R.string.invalid_password)
                    mView?.onLoading(false)
                }
            }
            else{
                mView?.showToast(R.string.invalid_user)
                mView?.onLoading(false)
            }
        }
    }

}