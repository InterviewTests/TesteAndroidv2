package com.accenture.santander.dashBoard


import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.santander.R
import com.accenture.santander.databinding.FragmentDashBoardBinding
import com.accenture.santander.login.DaggerLoginComponents
import com.accenture.santander.login.LoginModulo
import com.accenture.santander.recyclerview.adapter.StatementAdapter
import com.accenture.santander.utils.Coroutine
import com.accenture.santander.utils.StatusBar
import com.accenture.santander.viewmodel.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_dash_board.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class DashBoardFragment : Fragment(), DashBoardContracts.DashBoardPresenterOutput {

    private lateinit var binding: FragmentDashBoardBinding
    private lateinit var accounViewModel: AccountViewModel
    private lateinit var statements: StatementViewModel
    private val fragment: DashBoardFragment = this
    private lateinit var statementAdapter: StatementAdapter

    @Inject
    lateinit var iDashBoardPresenterInput: DashBoardContracts.DashBoardPresenterInput


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerDashBoardComponents
            .builder()
            .dashBoardModulo(DashBoardModulo(activity!!, binding.root, dashBoardFragment = this))
            .build()
            .inject(this)

        lifecycleScope.launch {
            StatusBar.setStatusBarColor(activity, ContextCompat.getColor(activity!!, R.color.colorPrimary))

            accounViewModel =
                activity?.run { ViewModelProviders.of(this).get(AccountViewModel::class.java) } ?: AccountViewModel()
            statements = activity?.run { ViewModelProviders.of(this).get(StatementViewModel::class.java) }
                ?: StatementViewModel()

            binding.account = accounViewModel.account
            statementAdapter = StatementAdapter(statements.statements)

            binding.dashBoardListStatement.apply {
                adapter = statementAdapter
                layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            }

            iDashBoardPresenterInput.searchLogout(activity!!)

            dash_board_img_logout.setOnClickListener {
                iDashBoardPresenterInput.logout()
            }

            dash_board_refrash_statements.setOnRefreshListener {
                iDashBoardPresenterInput.loadStatements()
            }

            iDashBoardPresenterInput.soliciteData()

            iDashBoardPresenterInput.loadStatements()
        }
    }

    override fun visibleRefrash() {
        dash_board_refrash_statements.isRefreshing = true
    }

    override fun goneRefrash() {
        dash_board_refrash_statements.isRefreshing = false
    }

    override fun apresentationData(user: LiveData<Account>) {
        user.observe(this, Observer {
            accounViewModel.account.agency = it.agency
            accounViewModel.account.balance = it.balance
            accounViewModel.account.bankAccount = it.bankAccount
            accounViewModel.account.name = it.name
        })
    }

    override fun loadLogout(drawable: Drawable) {
        binding.dashBoardImgLogout.setImageDrawable(drawable)
    }

    override fun apresentationStatements(statements: LiveData<MutableList<Statement>>) {
        statements.observe(this, Observer {
            this.statements.statements.clear()
            this.statements.statements.addAll(it)
            statementAdapter.notifyDataSetChanged()
        })
    }

    override fun cleanData() {
        this.statements.statements.clear()
        statementAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    override fun mensageLogout() {
        Toast.makeText(activity!!, R.string.logout_mensage, Toast.LENGTH_LONG).show()
    }

    override fun errorStatements() {
        Snackbar.make(binding.root, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun errorService(mensage: String?) {
        Snackbar.make(binding.root, mensage ?: activity!!.getText(R.string.fail_result_request), Snackbar.LENGTH_LONG).show()
    }

    override fun failRequest() {
        Snackbar.make(binding.root, R.string.fail_request, Snackbar.LENGTH_LONG).show()
    }

    override fun failNetWork() {
        Snackbar.make(binding.root, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun failImageLogout() {
        Toast.makeText(activity, R.string.fail_load_image, Toast.LENGTH_LONG).show()
    }
}
