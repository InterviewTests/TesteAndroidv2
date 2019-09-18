package com.develop.tcs_bank.infrastructure.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReturnStatement<T> (

    @SerializedName("statementList")
    @Expose
    var statement: T

)