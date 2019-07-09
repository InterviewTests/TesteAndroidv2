package br.com.learncleanarchitecture.util

interface OnResult<T> {
    fun onSuccess(response: T)
}