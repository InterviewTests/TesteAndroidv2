package pt.felipegouveia.bankapp.domain.common

import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable
import io.reactivex.Single

abstract class Mapper<in T,E>{

    abstract fun mapFrom(data: T): E

    fun single(data: T) = Single.fromCallable { mapFrom(data) }

    fun flowable(data: T) = Flowable.fromCallable { mapFrom(data) }

}