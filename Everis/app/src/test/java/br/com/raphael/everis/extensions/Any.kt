package br.com.raphael.everis.extensions

import com.google.gson.Gson


inline fun <reified T> Any.readJSON(fileName: String) =
    Gson().fromJson<T>(javaClass.classLoader!!.getResourceAsStream("json/${if (fileName.endsWith(".json")) fileName else "$fileName.json"}").bufferedReader().use { it.readText() })!!
