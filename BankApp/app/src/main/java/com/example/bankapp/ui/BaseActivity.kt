package com.example.bankapp.ui

import android.content.res.Resources
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp.R
import com.example.bankapp.ui.dialogs.DialogBuilder
import com.example.domain.base.TagExcecao
import com.example.domain.excecoes.ExecutorException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException

abstract class BaseActivity : AppCompatActivity() {
    open var viewProgressBar: View? = null

    private fun showLoading() {
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        viewProgressBar?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        viewProgressBar?.visibility = View.GONE
    }

    protected open fun exibirDialogErroPadrao(
        mensagem: String? = null,
        excecao: Exception? = null,
        acaoBotao: (() -> Unit)? = null
    ) {
        val mensagemDialog = obterMensagemDialogErro(mensagem, excecao)

        DialogBuilder(this).exibirDialogPadrao(
            getString(R.string.erro),
            mensagemDialog,
            acaoBotao
        )
    }

    private fun obterMensagemDialogErro(
        mensagem: String?,
        excecao: Exception?
    ): String {
        return if (mensagem.isNullOrBlank()) {
            if (excecao != null) {
                obterMensagemDialogErroPorExcecao(excecao)
            } else {
                getString(R.string.erro_nao_identificado)
            }
        } else {
            mensagem
        }
    }

    private fun obterMensagemDialogErroPorExcecao(
        excecao: Exception
    ): String {
        return when {
            excecao::class.java == Resources.NotFoundException::class.java ->
                getString(R.string.erro_not_found)
            excecao::class.java == TimeoutException::class.java ->
                getString(R.string.erro_timeout)
            excecao::class.java == ExecutorException::class.java -> {
                val executor = excecao as ExecutorException
                when {

                    executor.tag == TagExcecao.REALIZAR_LOGIN ->
                        getString(R.string.erro_realizar_login)
                    executor.tag == TagExcecao.LISTAR_STATEMENTS_POR_USUARIO ->
                        getString(R.string.erro_empresa_id)
                    else -> getString(R.string.erro_nao_identificado)
                }
            }
            else -> getString(R.string.erro_nao_identificado)
        }
    }

    protected fun doAsyncWork(
        work: suspend () -> Unit,
        acaoBotaoDialogErro: (() -> Unit)? = null,
        mostrarLoading: Boolean = true
    ) {
        GlobalScope.launch {
            try {
                if (mostrarLoading) {
                    runOnUiThread {
                        showLoading()
                    }
                }
                work()
            } catch (e: java.lang.Exception) {
                runOnUiThread {
                    exibirDialogErroPadrao(excecao = e, acaoBotao = acaoBotaoDialogErro)
                }
            } finally {
                if (mostrarLoading) {
                    runOnUiThread {
                        hideLoading()
                    }
                }
            }
        }
    }
}