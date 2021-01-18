package com.jeanjnap.data.util.moshi

import com.squareup.moshi.Moshi

interface InternalMoshi {
    fun getMoshi(): Moshi
}
