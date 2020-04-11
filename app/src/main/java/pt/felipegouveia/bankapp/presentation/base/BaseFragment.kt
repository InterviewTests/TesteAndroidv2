package pt.felipegouveia.bankapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import pt.felipegouveia.bankapp.util.ConnectionLiveData
import javax.inject.Inject

open class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    var networkAvailable: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionLiveData.observe(viewLifecycleOwner, Observer {
            networkAvailable = it
        })

    }


}