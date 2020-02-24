package br.com.flaviokreis.santanderv2.ui.utils

import android.text.format.DateFormat
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

@BindingAdapter("currency")
fun currencyFormat(textView: AppCompatTextView, value: Double) {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    textView.text = format.format(value)
}

@BindingAdapter("date")
fun dateFormat(textView: AppCompatTextView, value: String) {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    textView.text = format.parse(value)?.let { date ->
        DateFormat.getDateFormat(textView.context).format(date)
    } ?: value
}

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