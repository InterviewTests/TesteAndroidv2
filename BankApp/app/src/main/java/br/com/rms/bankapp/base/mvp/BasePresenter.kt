package br.com.rms.bankapp.base.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {

    protected var view: V? = null
    private lateinit var compositeDisposable: CompositeDisposable

    override fun attach(view: V) {
        this.view = view
        compositeDisposable = CompositeDisposable()
        view.getLifecycle().addObserver(this)
    }

    override fun detach() {
        view?.getLifecycle()?.removeObserver(this)
        view = null
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }


}