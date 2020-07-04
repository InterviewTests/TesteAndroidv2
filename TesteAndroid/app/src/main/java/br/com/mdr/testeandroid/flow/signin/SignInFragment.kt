package br.com.mdr.testeandroid.flow.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mdr.testeandroid.databinding.SignInFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {
    private lateinit var binding: SignInFragmentBinding
    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(inflater)
        return binding.root
    }

}