package br.com.mdr.testeandroid.flow.dashboard

import android.content.SharedPreferences
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
import br.com.mdr.testeandroid.extensions.showErrorSnack
import br.com.mdr.testeandroid.util.Constants.Companion.USER_KEY
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
    private val viewModel: DashboardViewModel by viewModel()
    private val adapter: StatementAdapter by inject()
    private var binding: DashboardFragmentBinding? = null
    private val preferencesEditor: SharedPreferences.Editor by inject()

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
        viewModel.statementsLive.observe(viewLifecycleOwner, Observer { statements ->
            statements?.let {
                adapter.replaceItens(it)
            }
        })

        viewModel.errorLive.observe( viewLifecycleOwner, Observer { error ->
            if (error?.code != 0)
                error?.message?.let { showErrorSnack(it) }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding?.apply { showLoading = it }
        })
    }

    private fun setupListeners(binding: DashboardFragmentBinding) {
        binding.apply {
            btnSignOut.setOnClickListener{
                signOutUser()
                viewModel.clickListener()
            }
        }
    }

    private fun signOutUser() {
        preferencesEditor.remove(USER_KEY)
        preferencesEditor.apply()
    }
}