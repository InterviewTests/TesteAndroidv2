package com.develop.zupp_bank.infrastructure.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ReturnAPI<T>(

    @SerializedName("userAccount")
    @Expose
    var user: T,

    @SerializedName("error")
    @Expose
    var error: T

)
