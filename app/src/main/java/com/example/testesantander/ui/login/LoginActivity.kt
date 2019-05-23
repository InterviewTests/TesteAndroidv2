package com.example.testesantander.ui.login

import android.content.Context
import android.content.Intent
import android.util.Base64
import android.view.View
import com.example.testesantander.R
import com.example.testesantander.domain.model.UserData
import com.example.testesantander.mvp.BaseActivity
import com.example.testesantander.ui.statements.StatementsActivity
import com.example.testesantander.utils.Decrypter
import com.example.testesantander.utils.Encrypter
import com.example.testesantander.utils.KeyboardUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity(), LoginContract.View {


    private val mPresenter: LoginContract.Presenter by inject()
    private lateinit var mUserData: UserData

    private var encrypter: Encrypter? = null
    private var decrypter: Decrypter? = null
    private var string64: String? = null
    private var iv64: String? = null
    private val ALIAS = "SANTANDER"
    private lateinit var stringByteArray: ByteArray
    private lateinit var ivByteArray: ByteArray

    override fun getLayoutResource(): Int {
        return R.layout.activity_login
    }

    override fun onInitView() {
        encrypter = Encrypter()
        decrypter = Decrypter()

        mPresenter.attach(this)
        loadListeners()

        val sp1 = this.getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val unm = sp1.getString("Unm", null)
        val mIV = sp1.getString("iv", null)
        if (!unm.isNullOrEmpty() && !mIV.isNullOrEmpty()) {
            stringByteArray = Base64.decode(unm, Base64.DEFAULT)
            ivByteArray = Base64.decode(mIV, Base64.DEFAULT)
            decryptText()
        }

    }

    private fun loadListeners() {
        btnSend.setOnClickListener {
            val user = etUser.text.toString()
            val password = etPassword.text.toString()
            mPresenter.checkValues(user, password)
            KeyboardUtil.hideKeyboard(this)
        }
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            pbLoading.visibility = View.VISIBLE
            btnSend.text = ""
        } else {
            pbLoading.visibility = View.GONE
            btnSend.text = resources.getString(R.string.login)
        }
    }

    override fun getAccountData(userData: UserData) {
        mUserData = userData
    }

    override fun login() {
        mPresenter.getUserCase()
    }

    override fun showToast(resId: Int) {
        showLongToast(getString(resId))
    }

    override fun callStatements() {
        val intent = Intent(this, StatementsActivity::class.java)
        intent.putExtra("userId", mUserData.userId)
        intent.putExtra("name", mUserData.name)
        intent.putExtra("bankAccount", mUserData.bankAccount)
        intent.putExtra("agency", mUserData.agency)
        intent.putExtra("balance", mUserData.balance)
        startActivity(intent)
        onLoading(false)
        finish()
    }

    override fun saveLogin() {
        encryptText(etUser.text.toString())
        val sp = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val Ed = sp.edit()
        Ed.putString("Unm", string64)
        Ed.putString("iv", iv64)
        Ed.apply()
    }

    private fun decryptText() {
        etUser.setText(decrypter?.decryptData(ALIAS, stringByteArray, ivByteArray))
    }

    private fun encryptText(user: String) {
        val encryptedText = encrypter!!.encryptText(ALIAS, user)
        val iv = encrypter!!.getIV()
        string64 = Base64.encodeToString(encryptedText, Base64.DEFAULT)
        iv64 = Base64.encodeToString(iv, Base64.DEFAULT)
    }

}
