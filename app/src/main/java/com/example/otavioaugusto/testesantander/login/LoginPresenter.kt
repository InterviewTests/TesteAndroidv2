package com.example.otavioaugusto.testesantander.login


import com.example.otavioaugusto.testesantander.utils.APIService
import com.example.otavioaugusto.testesantander.model.UserResponse
import com.example.otavioaugusto.testesantander.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class LoginPresenter(val view:LoginActivity) : LoginContrato.Presenter {


    override fun validarCPF(cpf: String): Boolean {
            return Pattern.matches(
                """\d{3}\.\d{3}\.\d{3}-\d{2}""",
                cpf
            )
        }


    override fun validar(user: String, password: String): Boolean {
        var isValid = true
        if (user.isNullOrEmpty()) {
            isValid = false
            view.mensagensErro("Campo vazio")
        } else
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                if (!validarCPF(user)) {
                    isValid = false
                   view.mensagensErro("Digite o e-mail ou CPF do usuário")
                } else null
            } else null


        if (password.isNullOrEmpty()) {
            isValid = false
            view.mensagensErro("Campo Vazio")
        } else if (validarPassword(password)) {
            isValid = false
            view.mensagensErro("Padrão de senha não reconhecido")
        } else null


        return isValid
    }

    override fun validarPassword(password: String): Boolean {
//        return Pattern.matches("""((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%]).{6,20})"""", password)
        //return Pattern.matches("""^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%^&+=])(?=\S+${'$'}).{6,}${'$'}""", password)

        return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%]).{6,20})", password)
    }

    override fun login(user: String, password: String) {
        view.showProgressBar()
        var call = RetrofitService
            .retrofit.create(APIService::class.java)
            .login(user,password)

        call.enqueue(object: Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.mensagensErro(t.message!!)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {

                    val user3 = response.body()!!.userAccount

                    view.user(user3)
                    view.hideProgressBar()
                }else{
                view.mensagensErro("Código"+response.code().toString())
            }
            }

        })

    }









}

