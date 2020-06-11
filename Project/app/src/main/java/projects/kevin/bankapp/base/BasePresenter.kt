package projects.kevin.bankapp.base

import projects.kevin.bankapp.user.service.qualifier.PostThreadExecutor
import projects.kevin.bankapp.user.service.qualifier.ThreadExecutor
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by caporal on 01/08/18.
 */
open class BasePresenter  constructor() {


    protected var compositeDisposable = CompositeDisposable()

    open fun destroy() {
        compositeDisposable.dispose()
    }


    protected fun  <Any> execute(single: Single<Any>): Single<Any> {
        return single
            .subscribeOn(ThreadExecutor(Schedulers.io()).scheduler)
            .observeOn(PostThreadExecutor(AndroidSchedulers.mainThread()).scheduler)
    }
}

