package com.example.henriquethomaziteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.henriquethomaziteste.apis.bankdata.BankApiManager
import com.example.henriquethomaziteste.events.BankLoginEvent
import com.example.henriquethomaziteste.helper.EventBus
import com.example.henriquethomaziteste.helper.UserDataValidator
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.register(this)
        setViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.unregister(this)
    }

    fun setViews(){
        main_button_login.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (UserDataValidator.validateInput(editTextPassword.text.toString())){
                BankApiManager.login(editTextUser.text.toString(), editTextPassword.text.toString())
            }
            else {
                Toast.makeText(this, "Senha Inválida: Deve conter Maiúsculas, Minúsculas, Caracteres Especiais e números", Toast.LENGTH_LONG).show()
            }

        }

        var savedData = BankApiManager.getUserCredentials(this)

        //Se houver dados salvos, recuperar e apresentar
        if (!savedData.user.isNullOrBlank() && !savedData.pass.isNullOrBlank()){
            editTextUser.setText(savedData.user)
            editTextPassword.setText(savedData.pass)
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginFinished(event: BankLoginEvent){
        progressBar.visibility = View.INVISIBLE
        if (!event.error!!){
            event.userData?.let { BankApiManager.storeUserCredentials(this, it, editTextUser.text.toString(), editTextPassword.text.toString()) }
            startActivity(Intent(this, AccountDetailsActivity::class.java))
        }
        else{
            Toast.makeText(this, "Houve Um Erro No Login", Toast.LENGTH_SHORT).show()
        }
    }
    

}