package com.ygorcesar.testeandroidv2.base.common.utils

import android.text.Editable
import android.text.InputFilter
import br.com.concrete.canarinho.formatador.Formatador
import br.com.concrete.canarinho.validator.Validador
import br.com.concrete.canarinho.validator.ValidadorCPFCNPJ
import br.com.concrete.canarinho.watcher.BaseCanarinhoTextWatcher

class CPFEmailTextWatcher : BaseCanarinhoTextWatcher() {

    private val validation = ValidadorCPFCNPJ.getInstance()
    private val resultParcial = Validador.ResultadoParcial()

    override fun afterTextChanged(s: Editable) {
        if (isMudancaInterna) return

        if (isCpf(s)) {
            s.filters = FILTER_CPF
            val builder = trataAdicaoRemocaoDeCaracter(s, MASK_CPF)
            atualizaTexto(validation, resultParcial, s, builder)
        } else {
            s.filters = FILTER_EMAIL
        }
    }

    private fun isCpf(e: Editable): Boolean {
        return Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(e).replaceAll("").length in 9..12 &&
                !RegexUtils.CHARACTERS.containsMatchIn(e)
    }

    companion object {
        private val MASK_CPF = "###.###.###-##".toCharArray()
        private val FILTER_EMAIL = arrayOf<InputFilter>(InputFilter.LengthFilter(40))
        private val FILTER_CPF = arrayOf<InputFilter>(InputFilter.LengthFilter(MASK_CPF.size))
    }
}