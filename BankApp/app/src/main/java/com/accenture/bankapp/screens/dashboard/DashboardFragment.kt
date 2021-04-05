package com.accenture.bankapp.screens.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.bankapp.R
import com.accenture.bankapp.network.models.Statement
import com.accenture.bankapp.network.models.UserAccount
import com.accenture.bankapp.screens.login.LoginFragment
import timber.log.Timber
import java.text.MessageFormat
import java.text.NumberFormat
import java.util.*

interface DashboardFragmentInput {
    fun displayDashboardMetadata(dashboardViewModel: DashboardViewModel)
    fun enableInfo(info: String)
    fun disableInfo()
}

interface DashboardFragmentListener {
    fun startLoginFragment(loginFragment: LoginFragment)
}

class DashboardFragment : Fragment(), DashboardFragmentInput {
    var listStatements: MutableList<Statement> = mutableListOf()
    lateinit var statementsRecyclerAdapter: StatementsRecyclerAdapter

    lateinit var dashboardRouter: DashboardRouter
    lateinit var dashboardInteractorInput: DashboardInteractorInput
    lateinit var dashboardFragmentListener: DashboardFragmentListener
    lateinit var userAccount: UserAccount

    lateinit var textName: TextView
    lateinit var textAccountAgency: TextView
    lateinit var textBalance: TextView
    lateinit var textDashboardInfo: TextView
    lateinit var recyclerStatements: RecyclerView
    lateinit var buttonLogout: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView: Creating the Dashboard Fragment View")

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        userAccount = this.arguments?.getSerializable("userAccount") as UserAccount

        DashboardConfigurator.configureFragment(this)
        bindViews(view)
        configureViews()
        displayUserData()
        fetchData()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            dashboardFragmentListener = activity as DashboardFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement DashboardFragmentListener")
        }
    }

    override fun displayDashboardMetadata(dashboardViewModel: DashboardViewModel) {
        Timber.i("displayDashboardMetaData: Displaying Dashboard metadata")

        listStatements.addAll(dashboardViewModel.listStatements!!)

        statementsRecyclerAdapter.notifyDataSetChanged()
    }

    override fun enableInfo(info: String) {
        textDashboardInfo.text = info
        recyclerStatements.visibility = View.GONE
        textDashboardInfo.visibility = View.VISIBLE
    }

    override fun disableInfo() {
        textDashboardInfo.text = ""
        textDashboardInfo.visibility = View.GONE
        recyclerStatements.visibility = View.VISIBLE
    }

    private fun bindViews(view: View) {
        Timber.i("bindViews: Binding Dashboard Fragment views")

        textName = view.findViewById(R.id.textName)
        textAccountAgency = view.findViewById(R.id.textAccountAgency)
        textBalance = view.findViewById(R.id.textBalance)
        textDashboardInfo = view.findViewById(R.id.textDashboardInfo)
        recyclerStatements = view.findViewById(R.id.recyclerStatements)
        buttonLogout = view.findViewById(R.id.buttonLogout)
    }

    private fun configureViews() {
        Timber.i("configureViews: Configuring the Dashboard Fragment views")

        statementsRecyclerAdapter = StatementsRecyclerAdapter(this.context!!, listStatements)
        recyclerStatements.layoutManager = LinearLayoutManager(this.context)
        recyclerStatements.adapter = statementsRecyclerAdapter

        buttonLogout.setOnClickListener(dashboardRouter)
    }

    private fun displayUserData() {
        Timber.i("displayUserData: Displaying user data")

        val formattedAgency = MessageFormat("{1}{2}.{3}{4}{5}{6}{7}{8}-{9}").format(userAccount.agency.split("").toTypedArray())
        val formattedBalance = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(userAccount.balance)

        textName.text = userAccount.name
        textAccountAgency.text = String.format("%s / %s", userAccount.bankAccount, formattedAgency)
        textBalance.text = formattedBalance
    }

    private fun fetchData() {
        dashboardInteractorInput.fetchDashboardData(DashboardRequest(userAccount.userId))
    }
}