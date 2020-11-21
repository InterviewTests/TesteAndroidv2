package com.example.bankapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.example.bankapp.R
import com.example.bankapp.ui.BaseActivity
import com.example.bankapp.ui.dialogs.DialogBuilder
import com.example.bankapp.ui.statements.MainActivity
import com.example.bankapp.util.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private var mUltimoClickBotaoLogin = 0L
    private val loginViewModel: LoginViewModel by viewModel()
    private val sessionManager: SessionManager = get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        configurarObserverLoginResposta()
    }

    private fun configurarObserverLoginResposta() {
        loginViewModel.loginResposta.observe(this, Observer {
            it?.let { resposta ->
                if (resposta.contaUsuario?.id != null) {
                    sessionManager.salvarInformacoesUsuario(
                        id = resposta.contaUsuario!!.id,
                        agencia = resposta.contaUsuario!!.agencia,
                        conta = resposta.contaUsuario!!.conta,
                        nome = resposta.contaUsuario!!.nome,
                        saldo = resposta.contaUsuario!!.saldo
                    )

                    val statementsIntent = Intent(this, MainActivity::class.java)
                    startActivity(statementsIntent)
                    finish()
                } else {
                    exibirMensagemErroLogin(mensagem = resposta.error?.mensagem!!)
                }
            }
        })
    }

    private fun setupListeners() {
        configurarEditTexts()
        configurarProgressBar()
        botao_efetuar_login.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mUltimoClickBotaoLogin > 1000) {
                mUltimoClickBotaoLogin = SystemClock.elapsedRealtime()
                if (loginViewModel.usuarioValido(loginViewModel.usuario.value) && loginViewModel.senhaValida(
                        loginViewModel.senha.value
                    )
                ) {
                    resetErrors()
                    efetuarLogin(
                        usuario = loginViewModel.usuario.value!!,
                        senha = loginViewModel.senha.value!!
                    )
                } else {
                    configurarErrosSenha(loginViewModel.senha.value)
                    configurarErrosUsuario(loginViewModel.usuario.value)
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
                        false -> loginViewModel.usuario.postValue(valor.toString())
                        else -> {
                        }
                    }
                }
            }
        })
        input_senha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editavel: Editable?) {}

            override fun beforeTextChanged(valor: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(valor: CharSequence?, p1: Int, p2: Int, p3: Int) {
                valor?.let {
                    when (valor.isBlank()) {
                        false -> loginViewModel.senha.postValue(valor.toString())
                        else -> Unit
                    }
                }
            }
        })
    }

    fun efetuarLogin(usuario: String, senha: String) {
        doAsyncWork(work = {
            loginViewModel.realizarLogin(
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

    private fun configurarErrosUsuario(valor: String?) {
        input_usuario.error = when (valor.isNullOrBlank()) {
            true -> "Campo de usuário não pode estar em branco."
            false -> if (!loginViewModel.usuarioValido(valor)) "O usuário deve conter um email ou CPF válido."
            else ""
        }
    }

    private fun configurarErrosSenha(valor: String?) {
        input_senha.error = when (valor.isNullOrBlank()) {
            true -> "Senha não pode estar em branco."
            false -> if (!loginViewModel.senhaValida(valor)) "A senha deve conter pelo menos uma letra maiúscula."
            else ""
        }
    }

    private fun resetErrors() {
        input_usuario.error = null
        input_senha.error = null
    }
}