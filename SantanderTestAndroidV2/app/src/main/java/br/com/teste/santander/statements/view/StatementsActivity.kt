package br.com.teste.santander.statements.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import br.com.teste.santander.R
import br.com.teste.santander.databinding.ActivityStatementsBinding
import br.com.teste.santander.model.UserAccount

class StatementsActivity : AppCompatActivity() {
    companion object {
        val USER_ACCOUNT_PARAM = "user_account_param"
    }

    private lateinit var binding: ActivityStatementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statements)

        val userAccount = intent.extras?.getParcelable<UserAccount>(USER_ACCOUNT_PARAM)
        binding.statemetsHeader.user = userAccount
    }

    fun logout(view: View) {
        super.onBackPressed()
    }
}
