package com.example.androidtest.data.api
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ResponseEntity(
    @SerializedName("userAccount") var userAccount: JsonObject,
    @SerializedName("statementList") var statementList: JsonArray,
    @SerializedName("error") var error: JsonObject)