package com.example.ibm_test.clean_code.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ibm_test.MainApplication
import com.example.ibm_test.R
import com.example.ibm_test.clean_code.home.interactor.HomeInteractorInput
import com.example.ibm_test.clean_code.login.ui.LoginActivity
import com.example.ibm_test.component.CustomAdapter
import com.example.ibm_test.component.UserItemViewHolder
import com.example.ibm_test.data.UserInfoData
import com.example.ibm_test.data.UserItemData
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(R.layout.activity_home), HomeActivityInput {

    @Inject
    lateinit var homeInteractorInput: HomeInteractorInput

    private val recyclerView by lazy{
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

        homeInteractorInput.startApp(userInfoData)
    }

    override fun loadUserItem(items: List<UserItemData>) {
        val adapter = CustomAdapter(
            items = items.toMutableList(),
            itemsViewHolder = { inflater, parent, _ ->
                val view = inflater.inflate(R.layout.user_item_view, parent, false)
                UserItemViewHolder(view)
            }
        )

        recyclerView.apply {
            this.visibility = View.VISIBLE
            this.layoutManager = linearLayout
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }
}
