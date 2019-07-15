package br.com.teste.santander.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import br.com.teste.santander.R

class Alert : AlertDialog.Builder {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, largura: Int) : super(context) {}

    companion object {

        /**
         * Show simples
         *
         * @param context
         * @param titulo
         * @param mensagem
         */
        fun show(context: Context, titulo: String, mensagem: String, cancelable: Boolean): AlertDialog {
            return Alert(context)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton(context.resources.getString(R.string.ok), null)
                .setCancelable(cancelable)
                .show()
        }

        /**
         * Show com opção
         *
         * @param context
         * @param titulo
         * @param mensagem
         * @param botaoPositivo
         * @param onClick
         * @return
         */
        fun show(
            context: Context,
            titulo: String,
            mensagem: String,
            botaoPositivo: String,
            onClick: DialogInterface.OnClickListener,
            cancelable: Boolean
        ): AlertDialog {
            return Alert(context)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setNegativeButton(context.resources.getString(R.string.cancelar), null)
                .setPositiveButton(botaoPositivo, onClick)
                .setCancelable(cancelable)
                .show()
        }

        /**
         * Show com opção
         *
         * @param context
         * @param titulo
         * @param mensagem
         * @param botaoPositivo
         * @param onClick
         * @return
         */
        fun showT(
            context: Context,
            titulo: String,
            mensagem: String,
            botaoPositivo: String,
            onClick: DialogInterface.OnClickListener,
            cancelable: Boolean
        ): AlertDialog {
            return Alert(context)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton(botaoPositivo, onClick)
                .setCancelable(cancelable)
                .show()
        }

        /**
         * Show com opção
         *
         * @param context
         * @param titulo
         * @param mensagem
         * @param botaoPositivo
         * @param onClick
         * @return
         */
        fun show(
            context: Context,
            titulo: String,
            mensagem: String,
            botaoPositivo: String,
            onClick: DialogInterface.OnClickListener,
            botaoNegativo: String,
            onClickNegativo: DialogInterface.OnClickListener,
            cancelable: Boolean
        ): AlertDialog {
            return Alert(context)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setNegativeButton(botaoNegativo, onClickNegativo)
                .setPositiveButton(botaoPositivo, onClick)
                .setCancelable(cancelable)
                .show()
        }

        /**
         * Show com opção
         *
         * @param context
         * @param titulo
         * @param mensagem
         * @param botaoPositivo
         * @param onClick
         * @return
         */
        fun show(
            context: Context,
            titulo: String,
            mensagem: String,
            botaoPositivo: String,
            onClick: DialogInterface.OnClickListener,
            botaoNegativo: String,
            onClickNegativo: DialogInterface.OnClickListener,
            botaoNeutro: String,
            onClickNeutro: DialogInterface.OnClickListener,
            cancelable: Boolean
        ): AlertDialog {
            return Alert(context)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setNeutralButton(botaoNeutro, onClickNeutro)
                .setNegativeButton(botaoNegativo, onClickNegativo)
                .setPositiveButton(botaoPositivo, onClick)
                .setCancelable(cancelable)
                .show()
        }


    }
}