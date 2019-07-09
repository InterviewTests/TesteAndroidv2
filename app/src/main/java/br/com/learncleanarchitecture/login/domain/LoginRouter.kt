package br.com.learncleanarchitecture.login.domain


import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.learncleanarchitecture.home.presentation.HomeFragment
import br.com.learncleanarchitecture.login.presentation.Login
import br.com.learncleanarchitecture.login.presentation.LoginFragment
import java.lang.ref.WeakReference


internal interface LoginRouterInput {
    fun determineNextScreen(login: Login?): Fragment?
    fun showNextScreen(nextFragment: Fragment?)
}

class LoginRouter : LoginRouterInput {

    var fragment: WeakReference<LoginFragment>? = null

    override fun showNextScreen(nextFragment: Fragment?) {
        nextFragment?.let {
            fragment?.get()?.homeFragmentListener?.startHomeFragment(nextFragment)
        }
    }

    override fun determineNextScreen(login: Login?): Fragment? {
        return if (login != null) {
            val nextFragment = HomeFragment()
            val args = Bundle()
            args.putParcelable("login", login)
            nextFragment.arguments = args
            return nextFragment
        } else {
            //todo: talvez pode ir pra outra tela se for o primeiro acesso
            null
        }
    }
}