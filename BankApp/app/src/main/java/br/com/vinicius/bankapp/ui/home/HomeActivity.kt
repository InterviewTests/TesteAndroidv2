package br.com.vinicius.bankapp.ui.home

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.internal.Preferences

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadUI()
        Preferences.getPreferences()?.let { it.userId?.let { id -> presenter.fetchListStatements(id) } }
    }

    private fun loadUI() {
        presenter = HomePresenter(this@HomeActivity)
    }

    override fun notification(message: String) {
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_LONG).show()
    }

}
