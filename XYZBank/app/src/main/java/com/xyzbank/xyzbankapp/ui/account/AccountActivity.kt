package com.xyzbank.xyzbankapp.ui.account

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.xyzbank.bankapp.data.model.UserInfo
import com.xyzbank.xyzbankapp.R
import com.xyzbank.xyzbankapp.commons.BuilderHelper
import com.xyzbank.xyzbankapp.data.model.AccountInfo
import com.xyzbank.xyzbankapp.data.model.JSONParser
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


private const val PARAM_ID = "id"
private const val PARAM_NAME = "name"
private const val PARAM_ACCOUNT = "account"
private const val PARAM_AGENCY = "agency"
private const val PARAM_BALANCE = "balance"

class AccountActivity : AppCompatActivity() {
    private lateinit var currency: Currency
    private lateinit var locale: Locale
    private var mListView: ListView? = null
    private lateinit var mAdapter: AcountEntriesAdapter
    private lateinit var decFormat: DecimalFormat
    private lateinit var dateFormat:SimpleDateFormat
    // TODO: Rename and change types of parameters
    private var userId: String? = null
    private var name: String? = null
    private var bankAccount: String? = null
    private var agency: String? = null
    private var balance: Double = 0.00


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        val extras = intent.extras
        if (extras != null) {

            userId =        extras.getString(PARAM_ID)
            name =          extras.getString(PARAM_NAME)
            bankAccount =   extras.getString(PARAM_ACCOUNT)
            agency =        extras.getString(PARAM_AGENCY)
            balance =       extras.getDouble(PARAM_BALANCE)
        }

        locale = Locale.getDefault()

        dateFormat = SimpleDateFormat("yyyy-MM-dd", locale)
        currency = Currency.getInstance(locale)
        mAdapter = AcountEntriesAdapter(this, locale, currency)

        findViewById<TextView>(R.id.tv_username).text = name
        val al = agency!!.length
        val fmtagency =
                agency!!.substring(0,2)+"."+
                agency!!.substring(2,al-1) + "-" +
                agency!!.substring(al-1,al)
        findViewById<TextView>(R.id.tv_bankaccount).text = "$bankAccount / $fmtagency"
        val bal = Formatter(locale).format("%.2f", balance)
        findViewById<TextView>(R.id.tv_balance).text = "${currency.getSymbol()} ${bal}"
        mListView = findViewById(R.id.list_entries)
        if (BuilderHelper().CheckNetwork(this))
            LoadAccountEntries().execute(userId)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
        return
    }

    /**
     * Use this inner calss to send GET request to the API
     * this fragment using the provided parameters.
     * this does not retun any object, just wait for API result and
     * create the GUI
     */
    inner class LoadAccountEntries : AsyncTask<String, String, Any>() {

        private lateinit var userid: String

        override fun onPreExecute() {
            Log.d("temp", "temp")
        }

        override fun doInBackground(vararg voids: String?): Any {
            userid = voids[0]!!
            // get the user account
            return JSONParser.getAccountEntries(userid)
        }

        override fun onPostExecute(results: Any?) {
            if (results != null) {
                // See Response in Logcat for understand JSON Results and make DeveloperList
                var acl = results as ArrayList<AccountInfo>
                for (c in acl) {
                    mAdapter.add(c)
                }
                mListView!!.adapter = mAdapter
            }
        }
    } // end of inner class

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this activity using the provided parameters.
         *
         * @param context: the login activity.
         * @param userinfo: the user info for this account entries.
         */
        // DONE: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(context: Context, userinfo: UserInfo): Intent {
            val detailIntent = Intent(context, AccountActivity::class.java)
            var arguments = Bundle().apply {
                putString(PARAM_ID, userinfo.userid.toString())
                putString(PARAM_NAME, userinfo.name)
                putString(PARAM_ACCOUNT, userinfo.bankaccount)
                putString(PARAM_AGENCY, userinfo.agency)
                putDouble(PARAM_BALANCE, userinfo.balance)
            }
            detailIntent.putExtras(arguments)
            return detailIntent
        }
    }
}