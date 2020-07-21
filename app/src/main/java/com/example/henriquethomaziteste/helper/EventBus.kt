package com.example.henriquethomaziteste.helper

import org.greenrobot.eventbus.EventBus


object EventBus {

    fun post(event: Any) {
        EventBus.getDefault().post(event)
    }

    fun register(subscriber: Any) {
        val eventBus = EventBus.getDefault()
        if (!eventBus.isRegistered(subscriber)) eventBus.register(subscriber)
    }

    fun unregister(subscriber: Any) {
        val eventBus = EventBus.getDefault()
        if (eventBus.isRegistered(subscriber)) eventBus.unregister(subscriber)
    }

}