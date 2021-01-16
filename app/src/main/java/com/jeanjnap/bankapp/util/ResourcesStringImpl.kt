package com.jeanjnap.bankapp.util

import android.content.Context
import com.jeanjnap.bankapp.R
import com.jeanjnap.domain.boundary.ResourcesString

class ResourcesStringImpl(
    private val context: Context
) : ResourcesString {
    override val genericError: String get() = context.getString(R.string.generic_error)
    override val noConnectionError: String get() = context.getString(R.string.no_connection_error)
    override val loading: String get() = context.getString(R.string.loading)
}
