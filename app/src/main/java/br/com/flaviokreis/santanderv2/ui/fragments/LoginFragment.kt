package br.com.flaviokreis.santanderv2.ui.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.flaviokreis.santanderv2.R
import br.com.flaviokreis.santanderv2.databinding.LoginFragmentBinding
import br.com.flaviokreis.santanderv2.di.Injectable
import br.com.flaviokreis.santanderv2.ui.utils.dismissKeyboard
import br.com.flaviokreis.santanderv2.viewmodels.LoginViewModel

class LoginFragment : BaseFragment<LoginFragmentBinding>(), Injectable {

    override val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun getLayout() = R.layout.login_fragment

    override fun bindView() {
        binding.viewModel = viewModel

        viewModel.onLoginResponse.observe(viewLifecycleOwner, Observer {
            // TODO going to statements screen
        })

        viewModel.onLoginInProgress.observe(viewLifecycleOwner, Observer {
            if(it) {
                activity?.dismissKeyboard()
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        })
    }
}
