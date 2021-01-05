package com.solinftec.desafiosantander_rafaelpimenta.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.solinftec.desafiosantander_rafaelpimenta.R
import com.solinftec.desafiosantander_rafaelpimenta.databinding.StatementsFragmentBinding
import com.solinftec.desafiosantander_rafaelpimenta.model.UserAccount
import com.solinftec.desafiosantander_rafaelpimenta.util.BindingAdapters
import com.solinftec.desafiosantander_rafaelpimenta.util.Helper
import com.solinftec.desafiosantander_rafaelpimenta.view.adapter.StatementsAdapter
import com.solinftec.desafiosantander_rafaelpimenta.viewmodel.StatementsViewModel
import kotlinx.android.synthetic.main.statements_fragment.*
import java.text.NumberFormat
import java.util.*

class StatementsFragment : Fragment() {

    private lateinit var viewModel: StatementsViewModel
    private lateinit var binding: StatementsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.statements_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatementsViewModel::class.java)

        binding.viewModel = viewModel
        binding.rvStatements.adapter =
            StatementsAdapter(
                emptyList()
            )

        binding.rvStatements.layoutManager = LinearLayoutManager(activity)
        setUserInfo()


        binding.btnLogout.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    private fun setUserInfo() {
       
        val user = arguments?.getSerializable("user") as UserAccount
        val balanceFormatted =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(user.balance)
        val agencyFormatted = Helper().agencyMask(user.agency)
        
        viewModel.nameText.set(user.name)
        viewModel.accountText.set("${user.bankAccount} / $agencyFormatted")
        viewModel.balanceText.set(balanceFormatted)
  
    }
}