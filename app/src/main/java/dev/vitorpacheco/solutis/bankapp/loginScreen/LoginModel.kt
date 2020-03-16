package dev.vitorpacheco.solutis.bankapp.loginScreen

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class LoginViewModel(
    val user: String? = null,
    val account: UserAccount? = null,
    val errorMessage: String? = null,
    val invalidUser: Boolean? = null,
    val invalidPassword: Boolean? = null
) : Parcelable

data class LoginRequest(var user: String?, var password: String?, var context: Context? = null)

@Parcelize
data class UserAccount(
    val userId: Int,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: BigDecimal?
) : Parcelable

@Parcelize
data class UserError(
    val message: String? = null
) : Parcelable

@Parcelize
data class LoginResponse(
    val userAccount: UserAccount? = null,
    val error: UserError? = null,
    val user: String? = null,
    val invalidUser: Boolean? = null,
    val invalidPassword: Boolean? = null
) : Parcelable
