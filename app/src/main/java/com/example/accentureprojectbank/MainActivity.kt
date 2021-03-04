package com.example.accentureprojectbank

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.graphics.Typeface.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import com.example.accentureprojectbank.Api.ServiceBuilder.getRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitClient = getRetrofitInstance()
        val endpoint = retrofitClient.create(Api::class.java)

        val etUser = findViewById<EditText>(R.id.et_user)
        val etPass = findViewById<EditText>(R.id.et_password)

        val btnLogin = findViewById<Button>(R.id.btn_login)

        val shared = getSharedPreferences("Save user", Context.MODE_PRIVATE)

        val textoDigitado = shared.getString("teste", "não encontrado")
        val setUser = findViewById<TextView>(R.id.et_user)
        setUser.text = textoDigitado

        val senhaDigitada = shared.getString("teste2", "não encontrado")
        val setSenha = findViewById<TextView>(R.id.et_password)
        setSenha.text = senhaDigitada

        btnLogin.setOnClickListener {


            if (validacao(etUser.text.toString(), etPass.text.toString())){

                val teste = bodyLogin(etUser.text.toString(), etPass.text.toString())

                val callback = endpoint.fazerLogin(teste)

//                Shared:
                shared.edit().putString("teste", etUser.text.toString()).apply()
                shared.edit().putString("teste2", etPass.text.toString()).apply()


                callback.enqueue(object : Callback<UserAccountResponse> {
                    override fun onFailure(call: Call<UserAccountResponse>, t: Throwable) {
                        val a = "deu erro"
                    }

                    override fun onResponse(call: Call<UserAccountResponse>, response: Response<UserAccountResponse>) {
                        response.body()?.let {
                            val x = it
                            val idUsuario = x.usuario

                            setarTexto(idUsuario)

                        }
                    }
                })

            } else {

                Toast.makeText(this, "O campo password deve validar se a senha tem pelo menos uma letra maiuscula, um caracter especial e um caracter alfanumérico", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun validacao(nome: String, senha: String) : Boolean {

        val userError = findViewById<EditText>(R.id.et_user)
        val passError = findViewById<EditText>(R.id.et_password)

        when {
            nome.isEmpty() -> {
                Toast.makeText(this, "Nome invalido", Toast.LENGTH_SHORT).show()
                userError.error = "Campo obrigatório"

                return false

            }
            senha.isEmpty() -> {
                Toast.makeText(this, "Senha invalida", Toast.LENGTH_SHORT).show()
                passError.error = "Campo obrigatório"
                return false

            }
            else -> {

                return passwordValidation(senha)

            }
        }

    }

    private fun setarTexto(dadosUsuario: User) {

        val ok = findViewById<EditText>(R.id.et_user)
//        ok.setText(texto)

        val intent = Intent(this, ExtratoActivity::class.java)

        intent.putExtra("id_user", dadosUsuario.idUsuario)
        intent.putExtra("count_user", dadosUsuario.contaBancaria)
        intent.putExtra("ag_user", dadosUsuario.agencia)
        intent.putExtra("value_user", dadosUsuario.saldo)
        intent.putExtra("nome_user", dadosUsuario.nome)

        startActivity(intent)

    }

    private fun passwordValidation(password: String): Boolean {
        return if (password.length > 1) {
            val letter: Pattern = Pattern.compile("[a-zA-z]")
            val digit: Pattern = Pattern.compile("[0-9]")
            val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
            val hasLetter: Matcher = letter.matcher(password)
            val hasDigit: Matcher = digit.matcher(password)
            val hasSpecial: Matcher = special.matcher(password)
            hasLetter.find() && hasDigit.find() && hasSpecial.find()
        } else false
    }
}
