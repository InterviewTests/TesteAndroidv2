package com.felipemsa.idletime.data

import com.orhanobut.hawk.Hawk

object DataStorage {

    const val USERNAME = "user_name"

    private var userAccount: UserAccount? = null

    fun setAccount(userAccount: UserAccount?) {
        DataStorage.userAccount = userAccount
    }

    fun getAccount(): UserAccount? = userAccount

    fun logout() {
        userAccount = null
    }

    fun saveUser(user: String) {
        save(USERNAME, user)
    }

    fun getUser(): String? =
        get(USERNAME)

    fun getUserId() : Int = userAccount!!.userId

    fun save(key: String, value: String) {
        Hawk.put(key, value)
    }

    fun get(key: String): String? = if (Hawk.contains(key)) Hawk.get(key) else null

}