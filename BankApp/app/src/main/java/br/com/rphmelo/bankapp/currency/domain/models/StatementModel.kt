package br.com.rphmelo.bankapp.currency.domain.models

import java.util.*

data class StatementModel( val title: String, val desc: String,
                           val date: Date,  val value: Double)