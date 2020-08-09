package com.xyzbank.xyzbankapp.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.xyzbank.xyzbankapp.R
import com.xyzbank.xyzbankapp.ui.login.LoginActivity
import java.util.*


class SplashScreen : AppCompatActivity() {

    private var animatedImgView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val lang = Locale.getDefault().language
        animatedImgView = findViewById(R.id.logo)


        splashScreenUseAsyncTask(lang)
    }

    // Show splash screen until network load data complete.
    private fun splashScreenUseAsyncTask(lang: String) {
        // Create a AsyncTask object.
        val retrieveDateTask = RetrieveDateTask()
        retrieveDateTask.execute(lang, "", "")
        // Create a count down timer object.It will count down every 0.1 seconds and last for milliSeconds milliseconds..
        var t = 0
        var um = 0
        val countDownTimer: CountDownTimer = object : CountDownTimer(9000, 100) {
            override fun onTick(l: Long) {
                if (um > 1)
                    when (t) {
                        in  0..48 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_h)
                        in 48..62 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_g)
                        in 62..64 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_f)
                        in 64..66 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_e)
                        in 66..68 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_d)
                        in 68..70 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_c)
                        in 70..72 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_b)
                        in 72..89 -> animatedImgView!!.setImageResource(R.drawable.ic_menino_a)
                    }
                t = (l / 100).toInt()
                um++
            }


            override fun onFinish() {
                if (!retrieveDateTask.isAsyncTaskComplete) {
                    start()
                } else {
                    for (i in 0..50) {
                        animatedImgView!!.setImageResource(R.drawable.ic_menino_g)
                        animatedImgView!!.setImageResource(R.drawable.ic_menino_g)
                        animatedImgView!!.setImageResource(R.drawable.ic_menino_g)
                        animatedImgView!!.setImageResource(R.drawable.ic_menino_h)
                        animatedImgView!!.setImageResource(R.drawable.ic_menino_g)
                        animatedImgView!!.setImageResource(R.drawable.ic_menino_h)
                    }
                    val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        // Start the count down timer.
        countDownTimer.start()
    }

    // This is the async task class that get data from network.
    private class RetrieveDateTask :
        AsyncTask<String?, String?, String?>() {
        // Indicate whether AsyncTask complete or not.
        var isAsyncTaskComplete = false
            private set

        // This method will be called before AsyncTask run.
        override fun onPreExecute() {
            isAsyncTaskComplete = false
        }

        // This method will be called when AsyncTask run.
        override fun doInBackground(vararg params: String?): String? {
            val result: String?
            try {
                val lang = params[0]!!.substring(0, 2)
                Log.d("temp", lang)
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                result = null
            }
            return result
        }

        // This method will be called after AsyncTask run.
        override fun onPostExecute(s: String?) {
            isAsyncTaskComplete = true
        }
    }
}
