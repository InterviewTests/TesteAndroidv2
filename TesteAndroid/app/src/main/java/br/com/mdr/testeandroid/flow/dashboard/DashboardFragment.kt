package br.com.mdr.testeandroid.flow.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.mdr.testeandroid.R
import br.com.mdr.testeandroid.adapter.StatementAdapter
import br.com.mdr.testeandroid.databinding.DashboardFragmentBinding
import br.com.mdr.testeandroid.extensions.setLightStatusBar
import br.com.mdr.testeandroid.extensions.setStatusBarColor
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
    private val viewModel: DashboardViewModel by viewModel()
    private val adapter: StatementAdapter by inject()
    private var binding: DashboardFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DashboardFragmentBinding.inflate(inflater)
        binding?.let {
            it.recyclerStatements.adapter = adapter
            setupListeners(it)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setStatusBarColor(R.color.colorAccent)
        activity?.setLightStatusBar(false)

        setupObservables()
        if (arguments != null) {
            arguments?.let {
                val user = DashboardFragmentArgs.fromBundle(it).usuario
                binding?.user = user
                viewModel.fetchUserStatements(user)
            }
        }
    }

    private fun setupObservables() {
        viewModel.dashboardHandler.dashboardPresenter.statementsLive.observe(viewLifecycleOwner, Observer { statements ->
            statements?.let {
                adapter.replaceItens(it)
            }
        })
    }

    private fun setupListeners(binding: DashboardFragmentBinding) {
        binding.apply {
            btnSignOut.setOnClickListener(viewModel.clickListener())
        }
    }
}