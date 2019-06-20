package br.com.vinicius.bankapp.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity;
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.FONT_HELVETICANEUE
import br.com.vinicius.bankapp.internal.FONT_HELVETICANEUELIGHT
import br.com.vinicius.bankapp.internal.USER_ACCOUNT

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter
    private lateinit var user:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadExtras()
        loadUI()
        loadFonts()
        user.userId?.let { id -> presenter.fetchListStatements(id) }
    }

    private fun loadExtras() {
        user = intent.getSerializableExtra(USER_ACCOUNT) as User
    }

    @SuppressLint("SetTextI18n")
    private fun loadUI() {
        presenter = HomePresenter(this@HomeActivity)
        textViewName.text = user.name
        textViewAccountNumber.text = "${user.bankAccount} / ${user.agency}"
        textViewBalanceNumber.text = "${user.balance}"
    }

    private fun loadFonts() {
        val fontLight = Typeface.createFromAsset(assets, FONT_HELVETICANEUELIGHT)
        val fontNormal = Typeface.createFromAsset(assets, FONT_HELVETICANEUE)
        textViewName.typeface = fontLight
        textViewAcountName.typeface = fontNormal
        textViewAccountNumber.typeface = fontLight
        textViewBalanceName.typeface = fontNormal
        textViewBalanceNumber.typeface = fontLight
    }

    override fun notification(message: String) {
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_LONG).show()
    }

}
