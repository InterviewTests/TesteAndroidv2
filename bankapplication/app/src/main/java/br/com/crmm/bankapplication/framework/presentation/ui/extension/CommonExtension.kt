package br.com.crmm.bankapplication.framework.presentation.ui.extension

import android.os.Handler
import android.os.Looper

fun runOnUiThread(run: () -> Unit) {
    Handler(Looper.getMainLooper()).post(run)
}
