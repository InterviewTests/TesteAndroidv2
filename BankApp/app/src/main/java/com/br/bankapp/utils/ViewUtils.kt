package com.br.bankapp.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import com.br.bankapp.R

object ViewUtils {
    fun toast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
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
        val agencyFormatted: String
        agencyFormatted =
            agency.substring(0, 2) + "." + agency.substring(2, 8) + "-" + agency.substring(8)
        return agencyFormatted
    }

}





