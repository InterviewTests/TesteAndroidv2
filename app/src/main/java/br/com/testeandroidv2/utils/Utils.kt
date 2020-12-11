package br.com.testeandroidv2.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    fun setStringConfig(context: Context, key: String, value: String) {
        var settings: SharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringConfig(context: Context, key: String?) : String? {
        var config: SharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        return config.getString(key, "")
    }

    @Suppress("DEPRECATION")
    fun isInternetConnect(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        }
        else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    }
                    else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    fun formatDecimal(number: Double?): String? {
        val locale = Locale("pt", "BR")
        val df = DecimalFormat("###,##0.00", DecimalFormatSymbols(locale))
            df.roundingMode = RoundingMode.FLOOR
        return df.format(number)
    }

    fun isValidPassword(password: String) : Boolean {
        val caracter = ".*[A-Z].*".toRegex()
        val number   = ".*[0-9].*".toRegex()
        val special  = ".*[#&\$*%@!].*".toRegex()

        if  (!caracter.matches(password))    { return false }
        if  (!number.matches(password))      { return false }
        else if (!special.matches(password)) { return false }

        return true
    }

    fun formatDate(date: String?): String? {
        val f0 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val f1 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        try {
            val c = GregorianCalendar(Locale.getDefault())
                c.time = f0.parse(date)
            return f1.format(c.time)
        }
        catch (pe: ParseException) {
        }
        return ""
    }

    fun getAgency(value: String): String? {
        val total = value.length
        var campo1 = ""
        var campo2 = ""
        var campo3 = ""
        for (c in 0 until total) {
            val character = value[c].toString()
            if (c < 2) {
                campo1 += character
            }
            else if (c < total - 1) {
                campo2 += character
            }
            else {
                campo3 += character
            }
        }
        return "$campo1.$campo2-$campo3"
    }
}