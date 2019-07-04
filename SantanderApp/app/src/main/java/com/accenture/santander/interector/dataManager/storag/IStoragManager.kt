package com.accenture.santander.interector.dataManager.storag

interface IStoragManager {

    fun setPreferences(key: String, value: String?)

    fun getPreferences(key: String): String

    fun cleanPreferences()
}