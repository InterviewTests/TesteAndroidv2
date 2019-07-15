package br.com.projetoaccenturebank.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import br.com.projetoaccenturebank.login.R

class SendIntent {

    private var mActivity: Activity? = null
    private var mClasse: Class<*>
    private var returnType: Int? = null
    private var animFrom: Int? = null
    private var animTo: Int? = null
    private var mBundle: Bundle? = null


    private constructor(sendIntentData: SendIntentData) {
        mActivity = sendIntentData.mActivity
        mClasse = sendIntentData.mClasse
        returnType = sendIntentData.returnType
        mBundle = sendIntentData.mBundle
    }

    companion object {
        fun with(): SendIntentData {
            val mSendData = SendIntentData()
            return mSendData
        }
    }

    /**
     * SUB CLASS PARA CAPTURAR OS DADOS
     */
    class SendIntentData {
        var mActivity: Activity? = null
        lateinit var mClasse: Class<*>
        var returnType: Int? = null
        var mBundle: Bundle? = null

        fun mClassFrom(activity: Activity): SendIntentData {
            this.mActivity = activity
            return this
        }

        fun mClassTo(classe: Class<*>): SendIntentData {
            this.mClasse = classe
            return this
        }

        fun mType(type: Int?): SendIntentData {
            this.returnType = type
            return this
        }

        fun mBundle(bundle: Bundle): SendIntentData {
            this.mBundle = bundle
            return this
        }

        fun go() {
            SendIntent(this).ir()
        }
    }

    /**
     * FUNCAO PARA EXECUTAR O INTENT
     */
    @SuppressLint("NewApi")
    fun ir() {

        animFrom = R.anim.fade_out
        animTo = R.anim.mover_direita

        if (returnType == R.integer.slide_from_right) {
            animFrom = R.anim.mover_esquerda
            animTo = R.anim.fade_in
        } else if (returnType == R.integer.slide_from_left) {
            animFrom = R.anim.fade_out
            animTo = R.anim.mover_direita
        }

        mActivity!!.window.exitTransition = null
        mActivity!!.window.enterTransition = null

        val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(mActivity!!, animFrom!!, animTo!!)

        val intent: Intent
        intent = Intent(mActivity, mClasse)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        if (mBundle != null)
            intent.putExtra("bundle", mBundle)

        ActivityCompat.startActivity(mActivity!!, intent, activityOptionsCompat.toBundle())
        mActivity!!.finish()
        //mActivity!!.overridePendingTransition(animFrom!!, animTo!!)
    }
}