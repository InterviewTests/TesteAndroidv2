package com.example.bankapp.features.details.presentantion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankapp.R
import com.example.bankapp.databinding.ActivityDetailsBinding
import com.example.bankapp.features.details.data.service.StatementResponse
import com.example.bankapp.features.details.presentantion.adapter.RecyclerAdapter
import com.example.bankapp.features.login.model.UserAccount
import com.example.base.extensions.hideToolbar
import com.example.base.extensions.requireParcelable
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    val userAccount by lazy { requireParcelable<UserAccount>(USER_BUNDLE) }
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsActivityViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProvider(this, factory).get(DetailsActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        fetchStatements()
        hideToolbar()
        setUpObserver()
    }

    private fun fetchStatements() {
        viewModel.fetchStatements()
    }

    private fun setUpObserver() {
        viewModel.screenState.observe(this, Observer {
            when (it) {
                is ScreenState.Statements -> configureList(it.statements)
                is ScreenState.Logoff -> showLogoffDialog()
                is ScreenState.Error -> showGenericError()
            }
        })
    }


    private fun configureList(news: List<StatementResponse>) {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recycler.apply {
            adapter = RecyclerAdapter(news)
            layoutManager = linearLayoutManager
        }
    }

    override fun onBackPressed() {
        showExitDialog()
    }

    private fun showLogoffDialog() {
        AlertDialog.Builder(this)
            .setMessage("Deseja realizar o Logoff?")
            .setNegativeButton("Não") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Sim") { _, _ -> logoff() }.show()
    }

    private fun logoff() {
        finish()
    }

    private fun showGenericError() {
        AlertDialog.Builder(this)
            .setMessage("Parece que estámos com um problema")
            .setNegativeButton("Sair") { _, _ -> finishAffinity() }
            .setPositiveButton("Tentar novamente") { _, _ -> fetchStatements() }.show()
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setMessage("Deseja sair?")
            .setNegativeButton("Não") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Sim") { _, _ -> finishAffinity() }.show()
    }

    companion object {
        const val USER_BUNDLE = "user bundle"

        @JvmStatic
        fun createIntent(context: Context, bundle: UserAccount): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtras(Bundle().apply {
                putParcelable(USER_BUNDLE, bundle)
            })
            return intent
        }
    }
}
