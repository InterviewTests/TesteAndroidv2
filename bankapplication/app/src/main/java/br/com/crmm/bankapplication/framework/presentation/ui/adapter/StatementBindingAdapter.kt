package br.com.crmm.bankapplication.framework.presentation.ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.crmm.bankapplication.R
import br.com.crmm.bankapplication.extension.applyBankAccountMask
import br.com.crmm.bankapplication.extension.nonNullable

@BindingAdapter(value = ["agency", "bankAccount"], requireAll = false)
fun setAgencyAndAccount(textView: TextView, agency: String?, account: String?) {
    textView.text = textView.context.getString(
        R.string.statement_header_agency_and_account,
        account.nonNullable(),
        agency.nonNullable().applyBankAccountMask()
    )
}