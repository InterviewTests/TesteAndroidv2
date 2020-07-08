package br.com.mdr.testeandroid.flow.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.mdr.testeandroid.databinding.DashboardFragmentBinding
import br.com.mdr.testeandroid.util.Constants.Companion.LOG_TAG
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
    private val viewModel: DashboardViewModel by viewModel()
    private var binding: DashboardFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DashboardFragmentBinding.inflate(inflater)
        binding?.let {
            setupListeners(it)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        if (arguments != null) {
            arguments?.let {
                val user = DashboardFragmentArgs.fromBundle(
                    it
                ).usuario
                viewModel.fetchUserStatements(user)
            }
        }
    }

    private fun setupObservables() {
        viewModel.dashboardHandler.dashboardPresenter.statementsLive.observe(viewLifecycleOwner, Observer { statements ->
            //TODO: Criar Adapter para mostrar lista de transações
            statements?.let {
                for (statement in it) {
                    Log.i(LOG_TAG, "TRANSAÇÃO: ${statement.desc}")
                }
            }
        })
    }

    private fun setupListeners(binding: DashboardFragmentBinding) {
        binding.apply {
            btnSignOut.setOnClickListener(viewModel.clickListener())
        }
    }
}