package com.develop.tcs_bank.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.develop.tcs_bank.R
import com.develop.tcs_bank.framework.base.TcsBaseActivity
import com.develop.tcs_bank.framework.base.TcsBaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

open class LoginView: TcsBaseFragment<TcsBaseActivity<*>>(), LoginContract.View{

    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.hideToolBar()
        presenter = LoginPresenter(this)

        //presenter.checkLogin()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.findViewById<EditText>(R.id.edUser).setText(presenter.checkLogin())
        activity.findViewById<EditText>(R.id.edPass).setText(presenter.checkPass())

        btnLogin.setOnClickListener {
            //presenter.callLogin(edUser.text.toString(),edPass.text.toString())
            presenter.processLogin(edUser.text.toString(),edPass.text.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)
        return view
    }

    override fun showMessage(msg: String) {
        activity.showToast(msg)
    }

    override fun setUser(us: String) {
        activity.findViewById<EditText>(R.id.edUser).setText(us)
    }


    override fun setPass(ps: String) {
        edPass.setText(ps)
    }

    override fun navigate() {
        activity.navigate(R.id.nav_data)
    }

}