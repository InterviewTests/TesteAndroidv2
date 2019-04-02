package br.com.rms.bankapp.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseActivity
import br.com.rms.bankapp.ui.login.LoginActivity
import dagger.android.AndroidInjector

class SplashActivity : BaseActivity() {

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun onInitViews() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_CANCELED){
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }


}
