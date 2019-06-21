package com.santander.app.core.util.validation

import io.reactivex.Completable

object ValidationManager {

    fun validation(validations: List<Pair<Boolean, String>>): Completable {
        return Completable.create { emitter ->
            validations.forEach { pair ->
                if (!pair.first && !emitter.isDisposed) {
                    emitter.onError(ValidationException(pair.second))
                }
            }
            if (validations.all { it.first }) {
                emitter.onComplete()
            }
        }
    }
}