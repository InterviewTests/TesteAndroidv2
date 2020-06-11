package projects.kevin.bankapp.base

import com.tech.fit.diet_plan.activity.qualifier.PostThreadExecutor
import com.tech.fit.diet_plan.activity.qualifier.ThreadExecutor
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

