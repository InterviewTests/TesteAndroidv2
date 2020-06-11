package projects.kevin.bankapp.user.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import projects.kevin.bankapp.R
import projects.kevin.bankapp.user.detail.DetailActivity
import projects.kevin.bankapp.utils.validatePassword

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            if(validatePassword(passLoginText.text.toString(), this)) {
                DetailActivity.startDetail(this)
            }
        }
    }
}
