package com.example.bankapp.view.mainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp.R
import com.example.bankapp.model.User
import com.example.bankapp.view.logged.LoggedActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this)

        txtUser.setText(mPresenter.getLogged())

        setListeners()

    }

    private fun setListeners(){
        btnLogin.setOnClickListener {
            mPresenter.doLogin(txtPassword.text.toString(), txtUser.text.toString())
        }
    }

    override fun logged(user: User){
        val intent = Intent(this, LoggedActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(LoggedActivity.USER, user)
        intent.putExtra(LoggedActivity.BUNDLE, bundle)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 0){
            txtUser.text.clear()
            txtPassword.text.clear()
        }
    }

    override fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
