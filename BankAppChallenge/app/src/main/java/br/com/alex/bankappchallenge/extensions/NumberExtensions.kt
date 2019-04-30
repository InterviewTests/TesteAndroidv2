package br.com.alex.bankappchallenge.extensions

import br.com.concrete.canarinho.formatador.FormatadorValor

fun Double.asBRL() = FormatadorValor.VALOR_COM_SIMBOLO.formata(this.toString())

