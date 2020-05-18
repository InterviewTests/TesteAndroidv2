package br.com.crmm.bankapplication.framework.presentation.ui.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.crmm.bankapplication.databinding.StatementFragmentLayoutBinding
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractFragment
import org.koin.core.inject

class StatementFragment : AbstractFragment() {

    private lateinit var binding: StatementFragmentLayoutBinding
    private val viewModel: StatementViewModel by inject()

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
}
