package br.com.bankapp.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import br.com.bankapp.R
import br.com.bankapp.data.LoginDelegate
import br.com.bankapp.data.LoginRes
import br.com.bankapp.data.LoginTask
import br.com.bankapp.model.Login
import br.com.bankapp.model.Usuario
import br.com.bankapp.util.Alert
import br.com.bankapp.util.Constantes
import br.com.bankapp.util.Mask
import br.com.bankapp.util.Util.Companion.isEmailValid
import br.com.bankapp.util.Util.Companion.isPasswordValid
import br.com.bankapp.util.Util.Companion.validaCPF
import br.com.bankapp.util.Util.Companion.verificaCaracter
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), LoginDelegate {

    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUser.addTextChangedListener(object : TextWatcher {
            var isUpdating : Boolean = false
            var oldString : String = ""
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (verificaCaracter(edtUser.text.toString())) {
                    edtUser.inputType = InputType.TYPE_CLASS_NUMBER
                    val str = Mask.replaceChars(s.toString())
                    var cpfWithMask = ""

                    if (count == 0)
                        isUpdating = true

                    if (isUpdating) {
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m: Char in Constantes.CPF_MASK.toCharArray()) {
                        if (m != '#' && str.length > oldString.length) {
                            cpfWithMask += m
                            continue
                        }
                        try {
                            cpfWithMask += str.get(i)
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    edtUser.setText(cpfWithMask)
                    edtUser.setSelection(cpfWithMask.length)
                }else{
                    edtUser.inputType = InputType.TYPE_CLASS_TEXT
                }

            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        btnLogin.setOnClickListener {

            if(!edtUser.text.isEmpty() && !edtPassword.text!!.isEmpty()){
                if (!isPasswordValid(edtPassword.text.toString())){
                    Alert.alertSimples(this, resources.getString(R.string.msg_valid_password))
                }else if (!verificaCaracter(edtUser.text.toString())){
                    if (!isEmailValid(edtUser.text.toString())){
                        Alert.alertSimples(this, resources.getString(R.string.msg_valid_email))
                    }else{
                        doLogin()
                    }
                }else{
                    if (!validaCPF(edtUser.text.toString())){
                        Alert.alertSimples(this, resources.getString(R.string.msg_valid_cpf))
                    }else {
                        doLogin()
                    }
                }
            }else{
                Alert.alertSimples(this, resources.getString(R.string.msg_campos_obrigatorios))
            }
        }

        getData()
    }

    private fun doLogin(){
        LoginTask(this, this).execute(getLoginObject())
    }

    private fun getLoginObject(): Login{
        val login = Login()
        login.uesrename = edtUser.text.toString()
        login.password = edtPassword.text.toString()
        return login
    }

    override fun onLoginResult(loginRes: LoginRes) {
        if (loginRes != null){
            if (loginRes.statusCode == HttpURLConnection.HTTP_OK){
                usuario = loginRes.usuario
                val intent = Intent(this, DetalhesLoginActivity::class.java)
                intent.putExtra("PARAM_USER", usuario)
                startActivity(intent)
                saveData()
            }else{
                Alert.alertSimples(this, loginRes.mensagem.toString())
            }
        }
    }

    //saving data
    fun saveData() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("user", edtUser.text.toString())
            putString("password", edtPassword.text.toString())
            commit()
        }
    }

    fun getData() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val str_user = sharedPref.getString("user", "")
        val str_password = sharedPref.getString("password", "")
        edtUser.setText(str_user)
        edtPassword.setText(str_password)
    }
}
