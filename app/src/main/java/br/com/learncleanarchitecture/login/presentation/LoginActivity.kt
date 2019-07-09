package br.com.learncleanarchitecture.login.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.learncleanarchitecture.R
import br.com.learncleanarchitecture.util.transact

class LoginActivity : AppCompatActivity(), LoginFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        showFragment(LoginFragment())
    }

    private fun showFragment(fragment: Fragment) {
        transact {
            replace(R.id.container, fragment)
            setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }

    override fun startHomeFragment(fragment: Fragment) {
        showFragment(fragment)
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}
