package com.example.santantest.domain.model

import java.io.Serializable

class UserAccount: Serializable {
    var userID: Int? = null

    var name: String? = null
    var bankAccount: String? = null
    var agency: String? = null
    var balance: Double? = null
}