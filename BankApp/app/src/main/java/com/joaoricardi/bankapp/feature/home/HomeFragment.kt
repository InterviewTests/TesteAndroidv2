package com.joaoricardi.bankapp.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.joaoricardi.bankapp.R
import com.joaoricardi.bankapp.extensions.asBRL
import com.joaoricardi.bankapp.models.login.UserAccont
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_card_info.*


class HomeFragment : Fragment() {

    private val viewModel:HomeViewModel  by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private val statementAdapter = HomeRecyclerAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutImageId.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        arguments?.getParcelable<UserAccont>(USER_ACCOUONT)?.let {
            userNameTextId.text = it.name
            userContaTextId.text = resources.getString(R.string.bankAccount, it.bankAccount, it.agency)
            userSaldoTextId.text = resources.getString(R.string.moneyValue, it.balance.asBRL(false))
        }

        with(statementsListRecyclerId){
            layoutManager = LinearLayoutManager(this.context)
            adapter = statementAdapter
        }

        viewModel.state.observeForever {state  ->
            when(state){
                is HomeViewModel.ScreenState.Loaded ->  statementAdapter.stateMents = state.value
                is HomeViewModel.ScreenState.Error -> Toast.makeText(context,resources.getString(R.string.homeToastError), Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        var TAG = "home_fragment"
        var USER_ACCOUONT = "USER_ACCOUNT"

        @JvmStatic
        fun newInstance(userAccont: UserAccont) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_ACCOUONT, userAccont)
                }
            }
    }
}
