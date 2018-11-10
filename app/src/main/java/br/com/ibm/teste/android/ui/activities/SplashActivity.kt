package br.com.ibm.teste.android.ui.activities

import android.content.Intent
import android.os.Handler
import br.com.ibm.teste.android.R
import br.com.ibm.teste.android.ui.generics.GenericActivity
import butterknife.ButterKnife

class SplashActivity : GenericActivity() {

    override fun setLayout() {
        setContentView(R.layout.act_splash)
        ButterKnife.bind(this)
    }

    override fun loadingMethods() {
        openMainActivity()
    }

    private fun openMainActivity() {
        Handler().postDelayed({
            startActivity( Intent(this, LoginActivity::class.java))
            finish()
        }, 2500)
    }
}
