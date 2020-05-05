package com.paulokeller.bankapp.data.repositories

interface  Repository {
    fun saveUser(user: String)
    fun getUser(): String?
}