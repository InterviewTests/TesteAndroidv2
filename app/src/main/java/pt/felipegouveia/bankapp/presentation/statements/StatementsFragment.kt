package pt.felipegouveia.bankapp.presentation.statements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.statements_fragment.*
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.databinding.StatementsFragmentBinding
import pt.felipegouveia.bankapp.presentation.base.BaseFragment
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.statements.adapter.StatementsAdapter
import pt.felipegouveia.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class StatementsFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var statementsAdapter: StatementsAdapter
    private val viewModel : StatementsViewModel by viewModels { viewModelFactory }
    private lateinit var binding : StatementsFragmentBinding

    private val params by navArgs<StatementsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatementsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setupUiObservers()
        viewModel.setUserAccount(params.userAccount)
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        statementsAdapter = StatementsAdapter()
        statements_recycler_statements.apply {
            layoutManager = linearLayoutManager
            adapter = statementsAdapter
        }
    }

    private fun setupUiObservers(){
        viewModel.statements.observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Status.ERROR -> {
                    binding.noResultsLayout.root.visibility = View.GONE
                    binding.errorOccurredLayout.root.visibility = View.VISIBLE
                    binding.statementsSwipeRefresh.isRefreshing = false
                }
                Status.SUCCESSFUL -> {
                    binding.statementsSwipeRefresh.isRefreshing = false
                    binding.errorOccurredLayout.root.visibility = View.GONE
                    it.data?.let { response ->
                        if (response.statementList?.isNotEmpty() == true) {
                            binding.noResultsLayout.root.visibility = View.GONE
                            statementsAdapter.update(response.statementList)
                        } else {
                            binding.noResultsLayout.root.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })

        binding.statementsSwipeRefresh.setOnRefreshListener {
            if(networkAvailable){
                viewModel.retry()
            } else {
                Snackbar.make(binding.root, R.string.login_error_unknown, Snackbar.LENGTH_LONG).show()
                binding.statementsSwipeRefresh.isRefreshing = false
            }
        }

    }
}