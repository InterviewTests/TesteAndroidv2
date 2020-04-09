package pt.felipegouveia.bankapp.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val subscriptions : CompositeDisposable = CompositeDisposable()

    fun add(disposable: Disposable){
        subscriptions.add(disposable)
    }

    private fun clear(){
        subscriptions.clear()
    }

    override fun onCleared() {
        clear()
    }
}