package br.com.crmm.bankapplication.framework.presentation.ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.crmm.bankapplication.R
import br.com.crmm.bankapplication.framework.presentation.ui.extension.safeRun
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("formatValue")
fun setFormatValue(textView: TextView, value: Double) {
    textView.text = textView.context.getString(R.string.formatted_value, value)
}

@BindingAdapter("formatDate")
fun setFormatDate(textView: TextView, date: String){
    val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    safeRun {
        oldFormat.parse(date)?.let {
            val formattedDate = newFormat.format(it)
            textView.text = formattedDate
        }
    }
}
