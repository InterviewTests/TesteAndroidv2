package br.com.flaviokreis.santanderv2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.com.flaviokreis.santanderv2.R
import br.com.flaviokreis.santanderv2.databinding.LoginFragmentBinding
import br.com.flaviokreis.santanderv2.di.Injectable

class LoginFragment : Fragment(), Injectable {

    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }
}
