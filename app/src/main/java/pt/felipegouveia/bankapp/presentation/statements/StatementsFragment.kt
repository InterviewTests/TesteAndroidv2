package pt.felipegouveia.bankapp.presentation.statements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import pt.felipegouveia.bankapp.databinding.StatementsFragmentBinding
import pt.felipegouveia.bankapp.presentation.base.BaseFragment
import pt.felipegouveia.bankapp.presentation.login.LoginFragmentArgs
import javax.inject.Inject

class StatementsFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel : StatementsViewModel by viewModels { viewModelFactory }
    private lateinit var binding : StatementsFragmentBinding

    private val params by navArgs<LoginFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatementsFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            this.lifecycleOwner = this@StatementsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUserId(params.userId)

    }
}