package br.com.teste.santander.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Handler
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.gson.Gson
import org.json.JSONObject
import java.io.*
import java.text.NumberFormat
import java.util.HashMap
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream


internal object SupportBase {

    var Context: android.content.Context? = null

    fun hideKeyBoard(context: Context, editText: EditText, time: Int) {
        val imm = context.getSystemService(Context!!.packageName) as InputMethodManager
        editText.clearFocus()
        Handler().postDelayed({ imm.hideSoftInputFromWindow(editText.windowToken, 0) }, time.toLong())
    }

    fun getXYActivity(activity: Activity): HashMap<String, Int> {
        val hashMap = HashMap<String, Int>()
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        hashMap["width"] = width
        hashMap["height"] = height

        return hashMap
    }

}
