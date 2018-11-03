package com.ygorcesar.testeandroidv2.base.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addToComposite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

/**
 * Extension function to subscribe on the background thread for a Flowable
 * */
fun <T> Flowable<T>.performOnBack(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to subscribe on the background thread for a Completable
 * */
fun Completable.performOnBack(): Completable {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Observable<T>.performOnBack(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Single<T>.performOnBack(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}

/**
 * Extension function to observe on computation thread for a Flowable
 * */
fun <T> Flowable<T>.observeOnComputation(): Flowable<T> {
    return this.observeOn(Schedulers.computation())
}

/**
 * Extension function to observe on computation thread for a Completable
 * */
fun Completable.observeOnComputation(): Completable {
    return this.observeOn(Schedulers.computation())
}

/**
 * Extension function to observe on computation thread for a Observable
 * */
fun <T> Observable<T>.observeOnComputation(): Observable<T> {
    return this.observeOn(Schedulers.computation())
}

/**
 * Extension function to observe on computation thread for a Single
 * */
fun <T> Single<T>.observeOnComputation(): Single<T> {
    return this.observeOn(Schedulers.computation())
}

/**
 * Extension function to subscribe on the background thread
 * and consumer a Throwable when throw an exception
 * */
fun Completable.peformOnBackAndCatchOnError(
    consumer: Consumer<Throwable>
): Completable {
    return this.performOnBack()
        .doOnError(consumer)
}

/**
 * Extension function to subscribe on the background thread,
 * apply a map to transform values and consumer a Throwable
 * when throw an exception
 * */
fun <T, R> Flowable<T>.peformOnBackMapAndCatchOnError(
    mapper: Function<T, R>,
    consumer: Consumer<Throwable>
): Flowable<R> {
    return this.performOnBack()
        .map(mapper)
        .doOnError(consumer)
}

/**
 * Extension function to subscribe on the background thread,
 * apply a map to transform values and consumer a Throwable
 * when throw an exception
 * */
fun <T, R> Observable<T>.peformOnBackMapAndCatchOnError(
    mapper: Function<T, R>,
    consumer: Consumer<Throwable>
): Observable<R> {
    return this.performOnBack()
        .map(mapper)
        .doOnError(consumer)
}

/**
 * Extension function to subscribe on the background thread,
 * apply a map to transform values
 * */
fun <T, R> Single<T>.peformOnBackMap(
    mapper: Function<T, R>
): Single<R> {
    return this.performOnBack()
        .map(mapper)
}

/**
 * Extension function to subscribe on the background thread,
 * apply a map to transform values and consumer a Throwable
 * when throw an exception
 * */
fun <T, R> Single<T>.peformOnBackMapAndCatchOnError(
    mapper: Function<T, R>,
    consumer: Consumer<Throwable>
): Single<R> {
    return this.performOnBack()
        .map(mapper)
        .doOnError(consumer)
}
