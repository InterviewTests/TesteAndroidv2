package com.br.myapplication.helper

import com.google.gson.Gson
import java.lang.reflect.Type

object AppHelper {

    fun convertObjToString(obj: Any): String = Gson().toJson(obj)

    fun <T> convertStringToObj(strObj: String, classOfT: Class<T>): T
            = Gson().fromJson(strObj, classOfT as Type)
}