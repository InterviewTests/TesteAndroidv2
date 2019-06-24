package br.com.vinicius.bankapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import br.com.vinicius.bankapp.domain.user.User
import com.google.android.material.snackbar.Snackbar

class LoginContract {

    interface View {
        fun notification(message: String)

        fun saveUserPreferences(user: User)

        fun showProgressBar(show: Boolean)

        fun getActivity(): AppCompatActivity

        fun showSnack(message: String)
    }

    interface Presenter {
        val view: LoginContract.View

        fun startLogin(username: String, password:String)

        fun showSnack(view: android.view.View, message: String, length: Int = Snackbar.LENGTH_LONG)
    }
}