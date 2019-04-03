package br.com.rms.bankapp.ui.home

import androidx.fragment.app.Fragment
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseActivity
import dagger.android.AndroidInjector

class HomeActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onInitViews() {
    }
}