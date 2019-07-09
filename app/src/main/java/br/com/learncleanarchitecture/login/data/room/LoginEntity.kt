package br.com.learncleanarchitecture.login.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "login_table")
data class LoginEntity(
    @PrimaryKey
    val userId: Int?,
    var name: String?,
    var bankAccount: String?,
    var agency: String?,
    var balance: Float?,

    var username: String?,
    var pass: String?

) : Serializable

