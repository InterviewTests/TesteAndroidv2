package com.earaujo.santander.statements

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.earaujo.santander.R
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.StatementsRepositoryImpl
import com.earaujo.santander.repository.Status.*
import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.models.UserAccountModel
import com.earaujo.santander.util.toBrl
import kotlinx.android.synthetic.main.activity_statements.*
import java.lang.ref.WeakReference

class StatementsActivity : AppCompatActivity(), StatementsActivityInput {

    lateinit var statementsInteractorInput: StatementsInteractorInput
    lateinit var statementsRouter: StatementsRouter
    private lateinit var adapter: StatementsListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)

        setupHomeActivity()

        statementsInteractorInput.fetchUserData(intent)
        statementsInteractorInput.fetchStatements()
    }

    fun setupHomeActivity() {
        statementsRouter = StatementsRouter()
        statementsRouter.activity = WeakReference(this)

        statementsInteractorInput = StatementsInteractor(StatementsRepositoryImpl()).also {
            it.statementsPresenterInput = StatementsPresenter().also {
                it.statementsActivityInput = WeakReference(this)
            }
        }

        btn_logout.setOnClickListener {
            statementsRouter.logout()
        }
    }

    override fun displayUserData(statementsResponse: UserAccountModel) {
        tv_username.text = statementsResponse.name
        tv_account.text = statementsResponse.bankAccount
        tv_balance.text = statementsResponse.balance.toBrl()
    }

    override fun displayStatementsData(statementsResponse: Resource<StatementsResponse>) {
        when(statementsResponse.status) {
            LOADING -> {
                //TODO Loading
            }
            SUCCESS -> {
                adapter = StatementsListAdapter(statementsResponse.data!!.statementList!!)
                linearLayoutManager = LinearLayoutManager(this)
                rv_statements.layoutManager = linearLayoutManager
                rv_statements.adapter = adapter
                nsv_statements.post { nsv_statements.scrollTo(0, 0) }
            }
            ERROR -> {
                //TODO Handle Error
            }
        }
    }

    companion object {
        val INTENT_USER_DATA = "user_data"
    }

}

interface StatementsActivityInput {
    fun displayUserData(statementsResponse: UserAccountModel)
    fun displayStatementsData(statementsResponse: Resource<StatementsResponse>)
}