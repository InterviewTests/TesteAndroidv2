package com.example.projetobank.ui.widget

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.example.projetobank.R
import kotlinx.android.synthetic.main.opcao_dialag.view.*

class AlertDialogFragment : DialogFragment() {

    private var titulo: String? = null
    private var mensagem: String? = null
    private var nomeBotaoPositivo: String? = null
    private var nomeBotaoNegativo: String? = null
    private var nomeBotaoNeutro: String? = null
    private var acaoNeutra: () -> Unit = {}
    private var acaoPositiva: () -> Unit = {}
    private var acaoNegativa: () -> Unit = {}

    fun setTitulo(titulo: String) {
        this.titulo = titulo
    }

    fun setMensagem(mensagem: String) {
        this.mensagem = mensagem
    }

    fun setBotaoNeutro(nome: String, acao: () -> Unit) {
        nomeBotaoNeutro = nome
        acaoNeutra = acao
    }

    fun setBotaoPositivo(nome: String, acao: () -> Unit) {
        nomeBotaoPositivo = nome
        acaoPositiva = acao
    }

    fun setBotaoNegativo(nome: String, acao: () -> Unit) {
        nomeBotaoNegativo = nome
        acaoNegativa = acao
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.opcao_dialag, null)!!
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setView(view)

        with(view) {
            dialog_titulo.text = titulo?.let { it }
            dialog_mensagem.text = mensagem?.let { it }
            dialog_positivo.text = nomeBotaoPositivo?.let { it }
            dialog_positivo.setOnClickListener { acaoPositiva() }
            dialog_negativo.text = nomeBotaoNegativo?.let { it }
            dialog_negativo.setOnClickListener { acaoNegativa() }
            dialog_neutro.text = nomeBotaoNeutro?.let { it }
            dialog_neutro.setOnClickListener { acaoNeutra() }
        }
        return alertDialog.create()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}