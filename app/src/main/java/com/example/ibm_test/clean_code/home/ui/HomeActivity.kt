package com.example.ibm_test.clean_code.home.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ibm_test.MainApplication
import com.example.ibm_test.R
import com.example.ibm_test.clean_code.home.interactor.HomeInteractorInput
import com.example.ibm_test.clean_code.home.presenter.HomePresenterInput
import com.example.ibm_test.clean_code.login.ui.LoginActivity
import com.example.ibm_test.component.CustomAdapter
import com.example.ibm_test.component.UserItemViewHolder
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.data.UserItemData
import com.example.ibm_test.utils.gone
import com.example.ibm_test.utils.show
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(R.layout.activity_home), HomeActivityInput {

    @Inject
    lateinit var homeInteractorInput: HomeInteractorInput

    @Inject
    lateinit var homePresenterInput: HomePresenterInput

    private val recyclerView by lazy {
        recycler_view
    }

    private val userInfoData by lazy {
        intent.getSerializableExtra(LoginActivity.USER_INFO_DATA_TO_INTENT) as UserInfoData
    }

    private val linearLayout = LinearLayoutManager(this).apply {
        this.orientation = RecyclerView.VERTICAL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as? MainApplication)?.applicationComponent?.inject(this)
        homePresenterInput.attachHomeInput(this)
        progress_bar_home.show()
        homeInteractorInput.startApp(userInfoData)

        btn_logout.setOnClickListener {
            homeInteractorInput.onLogout()
        }
    }

    override fun setUserList(items: List<UserItemData>) {
        val adapter = CustomAdapter(
            items = items.toMutableList(),
            itemsViewHolder = { inflater, parent, _ ->
                val view = inflater.inflate(R.layout.user_item_view, parent, false)
                UserItemViewHolder(view)
            }
        )

        progress_bar_home.gone()

        recyclerView.apply {
            this.show()
            this.layoutManager = linearLayout
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    override fun startActivityLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun setUserInfo(userName: String, numberAccount: String, balanceAccount: String) {
        user_name.text = userName
        number_account.text = numberAccount
        balance_account.text = balanceAccount
    }

    override fun setError(message: String) {
        progress_bar_home.gone()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
