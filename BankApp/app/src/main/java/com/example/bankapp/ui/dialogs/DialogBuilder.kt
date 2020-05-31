package com.example.bankapp.ui.dialogs

import android.content.Context
import com.example.bankapp.R

class DialogBuilder(val context: Context) {

    fun exibirDialogPadrao(
        titulo: String?,
        mensagem: String,
        acaoBotaoOk: (() -> Unit)? = null
    ) {
        DialogUmBotao(
            context,
            titulo,
            formatarMensagem(mensagem),
            context.getString(R.string.ok)
        ) {acaoBotaoOk?.invoke()}.show()
    }

    fun exibirDialogUmBotao(
        titulo: String?,
        mensagem: String,
        titleOkCall: String,
        okCall: () -> Unit
    ) {
        DialogUmBotao(
            context,
            titulo,
            formatarMensagem(mensagem),
            titleOkCall
        ) { okCall() }.show()
    }




    private fun formatarMensagem(mensagem: String): String {
        return mensagem.replace("\\\\n", "\n")
    }
}