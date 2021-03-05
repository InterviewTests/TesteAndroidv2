package br.com.silas.testeandroidv2.ui.statements

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.silas.domain.user.User
import br.com.silas.testeandroidv2.R
import br.com.silas.testeandroidv2.databinding.ActivityStatementsBinding

class StatementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatementsBinding

    companion object {
        private const val EXTRA_USER = "extra_user"
        fun start(context: Context, user: User) {
            val intent = Intent(context, StatementsActivity::class.java).apply {
                putExtra(EXTRA_USER, user)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statements)

        initializeItensAppBar()

    }

    private fun getUserExtra() = intent.getSerializableExtra(EXTRA_USER) as User

    private fun initializeItensAppBar() {
        val user = getUserExtra()

        binding.itemStatements.textViewUserName.text = user.name
        binding.itemStatements.textViewAccount.text = user.bankAccount.plus(" / " + user.agency)
        binding.itemStatements.textviewBalance.text = "R$ ${user.balance}"
    }
}