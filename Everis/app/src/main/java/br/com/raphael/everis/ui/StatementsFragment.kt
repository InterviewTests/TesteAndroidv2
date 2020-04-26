package br.com.raphael.everis.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import br.com.raphael.everis.R
import br.com.raphael.everis.viewmodel.StatementsViewModel

class StatementsFragment : Fragment() {

    private lateinit var viewModel: StatementsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statements, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatementsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerError()
        observerSuccess()
        observerLoading()
    }

    private fun observerError(){
    }

    private fun observerSuccess(){
    }

    private fun observerLoading(){
    }
}
