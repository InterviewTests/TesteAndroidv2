package br.com.projetoaccenturebank.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import br.com.projetoaccenturebank.login.R
import com.pnikosis.materialishprogress.ProgressWheel


/**
 * Created by Edson Ferreira on 18/10/2018.
 */


class Loading : AppCompatActivity() {

    interface Listener {
        fun onStart()

        fun onResume()

        fun onPause()

        fun onFinish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.loading)

        progress = this.findViewById(R.id.progress) as ProgressWheel
        lblMensagem = this.findViewById(R.id.lblMensagem) as TextView
        progressBar = this.findViewById(R.id.progressBar) as ProgressBar

        lblMensagem!!.visibility = View.GONE
        progressBar!!.visibility = View.GONE

        loading = this

        val bundle = intent.extras
        if (bundle != null) {
            lblMensagem!!.text = bundle.getString("texto")!!.toString()
            lblMensagem!!.visibility = View.VISIBLE
        }

        //Obtem tamanho da tela e ajusta content da tela
        val hashMap = SupportBase.getXYActivity(this)
        this.window.setLayout(hashMap.get("width")!!, hashMap.get("height")!!)
    }

    override fun onResume() {
        super.onResume()
        if (listener != null)
            listener!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (listener != null)
            listener!!.onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return false
    }

    companion object {

        private var loading: Loading? = null
        private var progress: ProgressWheel? = null
        private var lblMensagem: TextView? = null
        private var progressBar: ProgressBar? = null
        private var listener: Listener? = null

        fun show(context: Context, texto: String) {
            show(context, null, texto)
        }

        fun show(context: Context, listener: Listener?, texto: String) {
            Companion.listener = listener

            //Roda listener
            if (Companion.listener != null)
                Companion.listener!!.onStart()

            context.startActivity(Intent(context, Loading::class.java).putExtra("texto", texto))
        }

        fun hide() {
            if (loading != null) {

                //Finaliza loading
                loading!!.finish()

                //Roda listener
                if (listener != null)
                    listener!!.onFinish()

                loading = null
                lblMensagem = null
                progressBar = null
                listener = null
            }
        }

        fun setText(texto: String) {
            if (lblMensagem != null) {
                lblMensagem!!.visibility = View.VISIBLE
                lblMensagem!!.text = texto
            }
        }

        fun setProgressMax(value: Int) {
            if (progressBar != null) {
                progressBar!!.visibility = View.VISIBLE
                progressBar!!.progress = 0
                progressBar!!.max = value
                if (value == -1)
                    progressBar!!.visibility = View.GONE
                else
                    progressBar!!.visibility = View.VISIBLE
            }
        }

        fun setProgressValue(value: Int) {
            if (progressBar != null) {
                if (value >= progressBar!!.max)
                    progressBar!!.progress = 0
                else
                    progressBar!!.progress = value
            }
        }
    }
}
