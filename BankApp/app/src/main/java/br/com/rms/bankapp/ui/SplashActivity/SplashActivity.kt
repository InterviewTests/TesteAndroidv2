package br.com.rms.bankapp.ui.SplashActivity

import androidx.fragment.app.Fragment
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseActivity
import dagger.android.AndroidInjector

class SplashActivity : BaseActivity() {

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun onInitViews() {


    }


}
