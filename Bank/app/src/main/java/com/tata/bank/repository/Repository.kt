package com.tata.bank.repository

import com.tata.bank.login.LoginCredentials

class Repository {

    private var encryptedCredentials: HashMap<String, ByteArray>? = null

    fun saveCredentials(loginCredentials: LoginCredentials) {
        val login = "${loginCredentials.user}&${loginCredentials.password}"
        encryptedCredentials = Security.encrypt(login.toByteArray(Charsets.UTF_8))
    }

    fun getCredentials(): LoginCredentials? {
        encryptedCredentials?.let {
            val decryptedBytes = Security.decrypt(it)
            val decryptedData = decryptedBytes?.toString(Charsets.UTF_8)
            val loginDataList = decryptedData?.split("&")

            loginDataList?.let { credentialsList ->
                if (credentialsList.size == 2) {
                    return LoginCredentials(loginDataList[0], loginDataList[1])
                }
            }
        }

        return null
    }
}