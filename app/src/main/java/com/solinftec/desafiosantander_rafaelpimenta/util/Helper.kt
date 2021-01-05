package com.solinftec.desafiosantander_rafaelpimenta.util

import android.content.Context
import android.widget.Toast

class Helper {

    fun dateFormat(date: String): String {
        val s = date.split("-")
        return "${s[2]}/${s[1]}/${s[0]}"
    }

    fun toast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    fun agencyMask(agency: String): String {
        return agency.substring(0, 2) + "." + agency.substring(2, 8) + "-" + agency.substring(8)
    }
}