package br.com.rms.bankapp.base.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V: BaseContract.View>(private val view: V) : BaseContract.Presenter<V> {

    private lateinit var compositeDisposable: CompositeDisposable

    override fun attach(view: V) {
        compositeDisposable = CompositeDisposable()
        view.getLifecycle().addObserver(this)
    }

    override fun detach() {
        view.getLifecycle().removeObserver(this)
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }


}