package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment

class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {

    override fun getViewInstance(): HomeContract.View = this

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initViews() {

    }

}