package com.solinftec.desafiosantander_rafaelpimenta.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solinftec.desafiosantander_rafaelpimenta.R
import com.solinftec.desafiosantander_rafaelpimenta.viewmodel.StatementsViewModel

class StatementsFragment : Fragment() {

    companion object {
        fun newInstance() = StatementsFragment()
    }

    private lateinit var viewModel: StatementsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.statements_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatementsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}