package br.com.crmm.bankapplication.framework.presentation.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.core.KoinComponent

abstract class AbstractFragmentDataBinding<Binding : ViewDataBinding>(
    @LayoutRes private val contentLayoutId: Int
): AbstractFragment(), KoinComponent{

    protected lateinit var binding: Binding

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
    ): Binding {
        return DataBindingUtil.inflate(
            inflater,
            contentLayoutId,
            container,
            false
        )
    }

    abstract fun initBindingProperties()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingProperties()
    }
}