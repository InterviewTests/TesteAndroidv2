package com.solinftec.desafiosantander_rafaelpimenta.util

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.solinftec.desafiosantander_rafaelpimenta.R

class Helper {

    fun dateFormat(date: String): String {
        val s = date.split("-")
        return "${s[2]}/${s[1]}/${s[0]}"
    }

    fun toast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun show(view: View) {
        val progress = view.findViewById<RelativeLayout>(R.id.progress)
        progress.visibility = View.VISIBLE
    }

    fun hide(view: View) {
        val progress = view.findViewById<RelativeLayout>(R.id.progress)
        progress.visibility = View.GONE
    }

    fun agencyMask(agency: String): String {
        return agency.substring(0, 2) + "." + agency.substring(2, 8) + "-" + agency.substring(8)
    }
}