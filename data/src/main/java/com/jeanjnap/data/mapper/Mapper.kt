package com.jeanjnap.data.mapper

interface Mapper<CLASS_IN, CLASS_OUT> {
    fun transform(item: CLASS_IN?): CLASS_OUT
}
