package br.com.raphael.everis.extensions

import android.content.Context
import br.com.raphael.everis.R

fun Context.formatAgency(agency: String, bankAccount: String) : String {
    return this.getString(R.string.agencia_conta, bankAccount, agency.substring(0, 2), agency.substring(2, 8), agency.substring(8, 9))
}