package com.appdesafioSantander.bank.ui.statements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdesafioSantander.bank.R
import com.appdesafioSantander.bank.databinding.StatementsFragmentBinding
import com.appdesafioSantander.bank.model.UserAccount
import com.appdesafioSantander.bank.model.StatementsViewModel
import com.appdesafioSantander.bank.utils.ViewUtils
import java.text.NumberFormat
import java.util.*

class StatementsFragment : Fragment() {

    lateinit var statementsViewModel: StatementsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statementsViewModel = ViewModelProvider(this).get(StatementsViewModel::class.java)
        val binding = StatementsFragmentBinding.inflate(inflater, container, false)
        binding.statementsViewModel = statementsViewModel
        binding.rvStatements.adapter =
            StatementsAdapter(
                emptyList()
            )
        binding.rvStatements.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginBtn = view.findViewById<ImageButton>(R.id.logout_btn)
        setUserInfo(view)
        loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_Statements_to_Login)
        }
    }

    override fun onStart() {
        super.onStart()

        statementsViewModel.load()
    }

    private fun setUserInfo(view: View) {
        val nameTxt = view.findViewById<TextView>(R.id.name_text)
        val accountTxt = view.findViewById<TextView>(R.id.accont_text)
        val balanceTxt = view.findViewById<TextView>(R.id.balance_text)
        val user = arguments?.getSerializable("user") as UserAccount
        val balanceFormatted =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(user.balance)
        val agencyFormatted = ViewUtils.agencyMask(user.agency)
        nameTxt.text = user.name
        accountTxt.text = "${user.bankAccount} / $agencyFormatted"
        balanceTxt.text = balanceFormatted
    }

}