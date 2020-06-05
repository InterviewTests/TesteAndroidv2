package com.example.bankapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.example.bankapp.R
import com.example.bankapp.ui.BaseActivity
import com.example.bankapp.ui.dialogs.DialogBuilder
import com.example.bankapp.ui.statements.MainActivity
import com.example.bankapp.util.GerenciadorSessao
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private var mUltimoClickBotaoLogin = 0L
    private val realizarLoginViewModel: LoginViewModel by viewModel()
    private val gerenciadorSessao: GerenciadorSessao = get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        configurarObservers()
        configurarListeners()
    }

    private fun configurarObservers() {
        configurarObserverLoginResposta()
        configurarObserverSenha()
        configurarObserverUsuario()
    }

    private fun configurarObserverLoginResposta() {
        realizarLoginViewModel.loginResposta.observe(this, Observer {
            it?.let { resposta ->
                if (resposta.contaUsuario?.id != null) {
                    gerenciadorSessao.salvarInformacoesUsuario(
                        id = resposta.contaUsuario.id,
                        agencia = resposta.contaUsuario.agencia,
                        conta = resposta.contaUsuario.conta,
                        nome = resposta.contaUsuario.nome,
                        saldo = resposta.contaUsuario.saldo
                    )

                    val statementIntent = Intent(this, MainActivity::class.java)
                    startActivity(statementIntent)
                    finish()
                } else {
                    exibirMensagemErroLogin(mensagem = resposta.error.mensagem!!)
                }
            }
        })
    }

    private fun configurarObserverUsuario() {
        realizarLoginViewModel.usuario.observe(this, Observer {
            realizarLoginViewModel.formularioValido()
        })
    }

    private fun configurarObserverSenha() {
        realizarLoginViewModel.senha.observe(this, Observer {
            realizarLoginViewModel.formularioValido()
        })
    }

    private fun configurarListeners() {
        configurarEditTexts()
        configurarProgressBar()
        botao_efetuar_login.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mUltimoClickBotaoLogin > 1000) {
                mUltimoClickBotaoLogin = SystemClock.elapsedRealtime()
                if (realizarLoginViewModel.formularioValido.value!!) {
                    efetuarLogin(
                        usuario = realizarLoginViewModel.usuario.value!!,
                        senha = realizarLoginViewModel.senha.value!!
                    )

                }

            }

        }
    }

    private fun configurarEditTexts() {
        input_usuario.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editavel: Editable?) {
            }

            override fun beforeTextChanged(valor: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(valor: CharSequence?, p1: Int, p2: Int, p3: Int) {
                valor?.let {
                    when (valor.isBlank()) {
                        false -> realizarLoginViewModel.usuario.postValue(valor.toString())
                        else -> {
                        }
                    }

                }
            }
        })
        input_senha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editavel: Editable?) {
            }

            override fun beforeTextChanged(valor: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(valor: CharSequence?, p1: Int, p2: Int, p3: Int) {
                valor?.let {
                    when (valor.isBlank()) {
                        false -> realizarLoginViewModel.senha.postValue(valor.toString())
                        else -> {
                        }
                    }
                }
            }
        })
    }

    private fun efetuarLogin(usuario: String, senha: String) {
        doAsyncWork(work = {
            realizarLoginViewModel.realizarLogin(
                usuario = usuario,
                senha = senha
            )
        })
    }

    private fun configurarProgressBar() {
        viewProgressBar = progressBar
    }

    private fun exibirMensagemErroLogin(mensagem: String) {
        DialogBuilder(this).exibirDialogUmBotao(
            getString(R.string.erro),
            mensagem,
            getString(R.string.ok)
        ) { }
    }
}