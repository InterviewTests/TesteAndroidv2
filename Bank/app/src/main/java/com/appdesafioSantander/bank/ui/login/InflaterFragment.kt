package com.appdesafioSantander.bank.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appdesafioSantander.bank.R
import com.appdesafioSantander.bank.model.MainViewModel

class InflaterFragment : Fragment() {

    companion object {
        fun newInstance() = InflaterFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

}