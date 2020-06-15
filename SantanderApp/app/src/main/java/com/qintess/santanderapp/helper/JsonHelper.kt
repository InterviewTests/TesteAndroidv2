package com.qintess.santanderapp.helper

import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.forEach(callback: (JSONObject) -> Unit) {
    for (i in 0 until this.length()) {
        callback(this.getJSONObject(i))
    }
}