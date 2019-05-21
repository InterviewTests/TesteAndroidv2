package com.felipemsa.idletime.helper

import com.felipemsa.idletime.data.UserAccount
import com.orhanobut.hawk.Hawk

object DataStorage {

    const val USERNAME = "user_name"

    private var userAccount: UserAccount? = null

    fun setAccount(userAccount: UserAccount?) {
        this.userAccount = userAccount
    }

    fun getAccount(): UserAccount? = userAccount

    fun saveUser(user: String) {
        save(USERNAME, user)
    }

    fun getUser(): String? = get(USERNAME)

    fun getUserId() : Int = userAccount!!.userId

    fun save(key: String, value: String) {
        Hawk.put(key, value)
    }

    fun get(key: String): String? = if (Hawk.contains(key)) Hawk.get(key) else null

}