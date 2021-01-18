package com.jeanjnap.data.util.moshi

import com.squareup.moshi.Moshi

class InternalMoshiImpl : InternalMoshi {

    override fun getMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
