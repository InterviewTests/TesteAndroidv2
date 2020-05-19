package br.com.crmm.bankapplication.framework.presentation.ui.statement

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.crmm.bankapplication.R
import br.com.crmm.bankapplication.data.state.StatementDataState
import br.com.crmm.bankapplication.databinding.StatementFragmentLayoutBinding
import br.com.crmm.bankapplication.extension.nonNullable
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractFragmentDataBinding
import br.com.crmm.bankapplication.framework.presentation.ui.extension.safeRunOnUiThread
import br.com.crmm.bankapplication.framework.presentation.ui.login.LoginFragmentDirections
import br.com.crmm.bankapplication.framework.presentation.ui.statement.state.StatementViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementFragment : AbstractFragmentDataBinding<StatementFragmentLayoutBinding>(
    R.layout.statement_fragment_layout
) {

    private val viewModel: StatementViewModel by viewModel()
    private val adapter: StatementAdapter by lazy {
        StatementAdapter()
    }
    private val userAccount by lazy {
        StatementFragmentArgs.fromBundle(requireArguments()).userAccount
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        fetch()
    }

    override fun initBindingProperties() {
        binding.presentation = userAccount
        binding.viewModel = viewModel
    }

    private fun configureRecyclerView(){
        binding.statementRecyclerView.adapter = adapter
    }

    private fun fetch(){
        viewModel.fetch(userAccount.userId.nonNullable())
    }

    override fun onChangeState() {
        viewModel.statementDataState.observe(viewLifecycleOwner, Observer { state ->
            safeRunOnUiThread {
                when(state){
                    is StatementViewState.SuccessfullyLoadState -> {
                        adapter.addAll(checkNotNull(state.statementDataResponseDTO))
                    }
                    is StatementViewState.UnsuccessfullyLoadState -> {
                        toast("Load statements failed! - Code: ${state.errorResponseDTO.code}")
                    }
                    is StatementViewState.UnmappedErrorState -> {
                        toast("Unmapped error!")
                    }
                    is StatementViewState.LogoutState ->{
                        toast("Logout!")
                        findNavController().popBackStack()
                    }
                }
            }
        })
    }
}
