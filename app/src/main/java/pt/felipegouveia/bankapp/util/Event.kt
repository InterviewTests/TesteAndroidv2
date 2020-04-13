package pt.felipegouveia.bankapp.util

import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val value: T) {
    private val pending = AtomicBoolean(true)

    fun getIfPending(): T? {
        return if (pending.compareAndSet(true, false)) {
            value
        }
        else {
            null
        }
    }

    fun peek(): T = value

    fun isPending(): Boolean = pending.get()
}

