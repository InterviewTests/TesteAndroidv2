package br.com.crmm.bankapplication.framework.presentation.ui.common

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.crmm.bankapplication.framework.presentation.ui.extension.safeRun
import org.koin.core.KoinComponent

abstract class AbstractFragment: Fragment(), KoinComponent{

    protected abstract fun onChangeState()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onChangeState()
    }

    protected fun toast(msg: String){
        safeRun {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
    }
}