package com.example.otavioaugusto.testesantander.model

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("userAccount")
                        val userAccount: UserAccount,
                        @SerializedName("error")
                        val error: Error)