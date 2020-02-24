package br.com.flaviokreis.santanderv2.ui.utils

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("onEditorEnterAction")
fun onEditorEnterAction(editText: TextInputEditText, callback: () -> Unit) {
    editText.setOnEditorActionListener { v, actionId, event ->
        val imeAction = when (actionId) {
            EditorInfo.IME_ACTION_DONE,
            EditorInfo.IME_ACTION_SEND,
            EditorInfo.IME_ACTION_GO -> true
            else -> false
        }

        val keydownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
            && event.action == KeyEvent.ACTION_DOWN

        if (imeAction or keydownEvent)
            true.also { callback.invoke() }
        else false
    }
}