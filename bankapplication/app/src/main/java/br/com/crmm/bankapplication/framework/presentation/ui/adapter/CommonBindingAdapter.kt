package br.com.crmm.bankapplication.framework.presentation.ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.crmm.bankapplication.R

@BindingAdapter("formattedValue")
fun setFormattedValue(textView: TextView, value: Double) {
    textView.text = textView.context.getString(R.string.formatted_value, value)
}

@BindingAdapter("formattedAgencyAndAccount")
fun setAgencyAndAccount(textView: TextView, agency: String, account: String) {
    textView.text = textView.context.getString(
        R.string.statement_header_agency_and_account, agency, account
    )
}

