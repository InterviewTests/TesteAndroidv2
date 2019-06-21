package com.santander.domain.usecase.core

import io.reactivex.Completable
import io.reactivex.Observable

abstract class UseCase {

    object FromObservable {

        interface WithParameter<in P, R> {
            fun execute(params: P): Observable<R>
        }

        interface WithoutParameter<R> {
            fun execute(): Observable<R>
        }
    }

    object FromCompletable {

        interface WithParameter<in P> {
            fun execute(params: P): Completable
        }

        interface WithoutParameter {
            fun execute(): Completable
        }
    }
}