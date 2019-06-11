package com.zuptest.santander.data.local

interface Preferences {

    fun saveEmail(email: String)
    fun retrieveEmail() : String?
}