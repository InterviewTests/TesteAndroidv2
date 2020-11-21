package com.example.data.networking.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

object GsonUtil {
    private const val apiDateFormat = "yyyy-MM-dd'T'HH:mm:ss"
    val gsonDefault: Gson = GsonBuilder()
        .setDateFormat(apiDateFormat)
        .create()

    @Throws(JsonSyntaxException::class)
    inline fun <reified T> fromJson(json: String): T {
        return gsonDefault.fromJson(json, object : TypeToken<T>() {}.type)
    }
}