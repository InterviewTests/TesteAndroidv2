package br.com.flaviokreis.santanderv2.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.flaviokreis.santanderv2.R
import br.com.flaviokreis.santanderv2.databinding.FragmentStatementListBinding
import br.com.flaviokreis.santanderv2.di.Injectable
import br.com.flaviokreis.santanderv2.ui.adapters.StatementRecyclerViewAdapter
import br.com.flaviokreis.santanderv2.viewmodels.StatementViewModel

class StatementsFragment : BaseFragment<FragmentStatementListBinding>(), Injectable {

    override val viewModel: StatementViewModel by viewModels { viewModelFactory }

    override fun getLayout() = R.layout.fragment_statement_list

    override fun bindView() {
        binding.viewModel = viewModel

        viewModel.hasUserAccount().observe(viewLifecycleOwner, Observer {
            if (!it) {
                navigateToLogin()
            }
        })

        binding.header.logout.setOnClickListener {
            viewModel.logout()
            navigateToLogin()
        }

        viewModel.getStatements().observe(viewLifecycleOwner, Observer {
            binding.statementsList.adapter = StatementRecyclerViewAdapter(it.statementList)
        })
    }

    private fun navigateToLogin() {
        findNavController().navigate(
            StatementsFragmentDirections.actionStatementsFragmentToLoginFragment())
    }

}
