package br.com.bank.android.exceptions

import android.content.Context

open class BusinessError : RuntimeException {
    private var messageRes: Int? = null

    constructor(messageRes: Int) : super() {
        this.messageRes = messageRes
    }

    constructor(message: String?) : super(message)

    fun getMessage(context: Context): String {
        messageRes?.let {
            return context.getString(it)
        }.also {
            return message ?: ""
        }
    }
}