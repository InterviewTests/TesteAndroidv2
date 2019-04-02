package br.com.rms.bankapp.ui.login

import androidx.fragment.app.Fragment
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class LoginActivity : BaseActivity() {



    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onInitViews() {

    }
}
