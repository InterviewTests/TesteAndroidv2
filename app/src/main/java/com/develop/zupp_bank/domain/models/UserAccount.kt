package com.develop.zupp_bank.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserAccount(

 /*   @SerializedName("userAccount")
    val userAccount: UserAccount,

    @SerializedName("error")
    val error: String
 */

 @SerializedName("userId")
    @Expose val userId: Int,

 @SerializedName("name")
    @Expose val name: String,

 @SerializedName("bankAccount")
    @Expose val bankAccount: Int,

 @SerializedName("agency")
    @Expose val agency: String,

 @SerializedName("balance")
    @Expose val balance: Float


)