package com.example.bankapp.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
import com.example.bankapp.R
import kotlinx.android.synthetic.main.base_dialog_um_botao.*

class DialogUmBotao(
    context: Context,
    titulo: String?,
    mensagem: String,
    textoBotaoPositivo: String,
    val acaoBotaoPositivo: () -> Unit
) : Dialog(context) {

    init {
        setContentView(R.layout.base_dialog_um_botao)
        setCancelable(false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT
        window?.attributes = lp

        textoTitulo.text = titulo ?: ""
        textoMensagem.text = mensagem

        botaoPositivo.text = textoBotaoPositivo
        botaoPositivo.setOnClickListener {
            dismiss()
            acaoBotaoPositivo()
        }
    }
}