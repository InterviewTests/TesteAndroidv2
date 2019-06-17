package com.accenture.santander.login


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.accenture.santander.viewmodel.User
import com.accenture.santander.viewmodel.UserViewModel
import com.accenture.santander.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import android.os.Build
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import com.accenture.santander.R
import com.accenture.santander.interector.remote.service.login.ServiceLogin
import com.accenture.santander.utils.StatusBar
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class LoginFragment : Fragment(), LoginContracts.LoginPresenterOutput {

    @Inject
    lateinit var iLoginPresenterInput: LoginContracts.LoginPresenterInput

    private val fragment: LoginFragment = this
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        StatusBar.setStatusBarColor(activity, Color.WHITE)
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).window.getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerLoginComponents
            .builder()
            .loginModulo(LoginModulo(activity!!, binding.root, loginFragment = this))
            .build()
            .inject(this)

        lifecycleScope.launch {

            userViewModel =
                activity?.run { ViewModelProviders.of(this).get(UserViewModel::class.java) } ?: UserViewModel()
            binding.user = userViewModel.user

            iLoginPresenterInput.searchLogo(activity!!)

            iLoginPresenterInput.searchData()

            login_btn_login.setOnClickListener {
                    iLoginPresenterInput.login(userViewModel.user)
            }
        }
    }

    override fun loadLogo(drawable: Drawable) {
        binding.loginImgLogo.setImageDrawable(drawable)
    }

    override fun invalidePassword() {
        binding.loginEditPassword.error = activity?.getString(R.string.invalide_password)
    }

    override fun invalideLogin() {
        binding.loginEditLogin.error = activity?.getString(R.string.invalide_login)
    }

    override fun resultData(user: User) {
        userViewModel.user.login = user.login
        userViewModel.user.password = user.password
    }

    override fun visibleProgress() {
        login_progress_circular_login?.visibility = ProgressBar.VISIBLE
    }

    override fun goneProgress() {
        login_progress_circular_login?.visibility = ProgressBar.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    override fun errorLogin() {
        Snackbar.make(binding.root, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun errorService(mensage: String?) {
        Snackbar.make(binding.root, mensage ?: activity!!.getText(R.string.fail_result_request), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun failRequest() {
        Snackbar.make(binding.root, R.string.fail_request, Snackbar.LENGTH_LONG).show()
    }

    override fun failNetWork() {
        Snackbar.make(binding.root, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun failLoadImage() {
        Toast.makeText(activity, R.string.fail_load_image, Toast.LENGTH_LONG).show()
    }
}
