package br.com.ibm.teste.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import br.com.ibm.teste.android.app.IbmTestApplication
import java.text.NumberFormat

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:46
 */
class Utils {



    companion object {

        private const val pipe:  String = " / "
        private var numberFormat = NumberFormat.getCurrencyInstance()

        /**
         * Check if there is internet on the phone
         */
        fun isConnected(mContext: Context): Boolean {
            val connectivityManager =
                    mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo?.isConnected ?: false
        }

        /**
         * Show message on Activity
         */
        fun showMessage(message: String) {
            Toast.makeText(IbmTestApplication.getInstance(), message, Toast.LENGTH_SHORT).show()
        }

        fun formatBankAccount(bankAccount: String, agency: String) : String {
            val formattedAccount : String

            val firstValue = agency.substring(0, 2)
            val secondValue = agency.substring(2, 8)
            val lastValue = agency.substring(agency.length - 1)
            formattedAccount = "$bankAccount$pipe$firstValue.$secondValue-$lastValue"

            return formattedAccount
        }

        fun formatNumber(any: Any?): String = numberFormat.format(any)
    }
}