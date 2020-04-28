package br.com.raphael.everis.helpers

import android.content.Context
import br.com.raphael.everis.R

class FormatarAgency {
    companion object {
        fun formatAgency(context: Context, agency: String, bankAccount: String) : String {
            return context.getString(R.string.agencia_conta, bankAccount, agency.substring(0, 2), agency.substring(2, 8), agency.substring(8, 9))
        }
    }
}