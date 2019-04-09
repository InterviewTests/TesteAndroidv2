package br.com.rms.bankapp.base.mvp

interface BaseView<V: BaseContract.View, P: BaseContract.Presenter<V>> {

    fun getViewInstance(): V

    //fun newPresenterInstance():P

    fun getLayoutId(): Int

    fun initViews()

}