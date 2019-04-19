package br.com.rms.bankapp.base.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V: BaseContract.View>(private val view: V) : BaseContract.Presenter {

    private lateinit var compositeDisposable: CompositeDisposable

    override fun attach() {
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