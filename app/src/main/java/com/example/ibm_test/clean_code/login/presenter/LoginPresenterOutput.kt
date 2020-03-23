package com.example.ibm_test.clean_code.login.presenter

import android.content.Context
import com.example.ibm_test.R
import com.example.ibm_test.clean_code.login.ui.LoginActivityInput
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.utils.hasOneUpperCase
import java.lang.ref.WeakReference

class LoginPresenterOutput constructor(private val context: Context) : LoginPresenterInput {

    private lateinit var weakReference: WeakReference<LoginActivityInput>

    private val input: LoginActivityInput?
        get() = weakReference.get()

    override fun attachLoginInput(loginActivityInput: LoginActivityInput) {
        weakReference = WeakReference(loginActivityInput)
    }

    override fun setDataCredentials(user: String, password: String) {
        when {
            user.trim().isEmpty() -> {
                input?.setupErrorToFieldUser(context.getString(R.string.alert_to_field_empty))
            }
            password.trim().isEmpty() -> {
                input?.setupErrorToFieldPassword(context.getString(R.string.alert_to_field_empty))

            }
            password.hasOneUpperCase() -> {
                input?.setupErrorToFieldPassword(context.getString(R.string.missing_upper_case))
            }
        }
    }

    override fun onSuccess(userInfoData: UserInfoData) {
        input?.startActivityHome(userInfoData)
    }

    override fun onError(error: Throwable) {
        input?.messageError(error.message.toString())
    }
}