package br.com.crmm.bankapplication.framework.presentation.ui.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.crmm.bankapplication.data.state.StatementDataState
import br.com.crmm.bankapplication.databinding.StatementFragmentLayoutBinding
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractFragment
import br.com.crmm.bankapplication.framework.presentation.ui.extension.safeRunOnUiThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementFragment : AbstractFragment() {

    private lateinit var binding: StatementFragmentLayoutBinding
    private val viewModel: StatementViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        return binding.root
    }

    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = StatementFragmentLayoutBinding.inflate(
        inflater, container, false
    ).apply {
        lifecycleOwner = this@StatementFragment.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch()
    }

    private fun fetch(){
        viewModel.fetch()
    }

    override fun onChangeState() {
        viewModel.statementDataState.observe(viewLifecycleOwner, Observer { state ->
            safeRunOnUiThread {
                when(state){
                    is StatementDataState.SuccessfullyResponseState -> {
                        toast("load statements!")
                    }
                    is StatementDataState.UnsuccessfullyResponseState -> {
                        toast("Load statements failed! - Code: ${state.errorResponseDTO.code}")
                    }
                    is StatementDataState.UnmappedErrorState -> {
                        toast("Unmapped error!")
                    }
                }
            }
        })
    }

}
