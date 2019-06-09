package com.androiddeveloper.santanderTest.data.model.userdata

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class LoginResponse(var userAccount: Data?, var error: LoginError?)

data class Data(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
) : Serializable

@Entity
data class EncryptedData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    var data: String = ""
)

data class LoginError(
    var code: Int,
    var message: String
)

data class DataDTO(
    var userId: Int = -1,
    var name: String = "",
    var bankAccount: String = "",
    var agency: String = "",
    var balance: String = ""
)

