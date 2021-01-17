package com.jeanjnap.bankapp.util.bindadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.util.extensions.formatAgency
import com.jeanjnap.domain.util.extensions.formatCurrency
import com.jeanjnap.domain.util.extensions.formatToString
import java.math.BigDecimal
import java.util.Date

@BindingAdapter("nullableText")
fun nullableText(tv: TextView, value: String?) {
    value?.let {
        tv.text = it
    }
}
@BindingAdapter("formatAccount")
fun formatAccount(tv: TextView, value: UserAccount?) {
    value?.let {
        tv.text = "${it.bankAccount} / ${it.agency?.formatAgency()}"
    }
}

@BindingAdapter("formatCurrency")
fun formatCurrency(tv: TextView, value: BigDecimal?) {
    value?.let {
        tv.text = it.formatCurrency()
    }
}

@BindingAdapter("formatDate")
fun formatDate(tv: TextView, value: Date?) {
    value?.let {
        tv.text = it.formatToString()
    }
}