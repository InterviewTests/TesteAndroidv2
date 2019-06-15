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
import org.junit.Test
import org.junit.Assert.*
import android.os.Build
import android.view.*
import com.cy.translucentparent.StatusNavUtils


class LoginFragment : Fragment(), LoginContracts.LoginPresenterOutput {

    private lateinit var iLoginPresenterInput: LoginContracts.LoginPresenterInput
    private val fragment: LoginFragment = this
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        StatusNavUtils.setStatusBarColor(activity, Color.WHITE)
        (activity as AppCompatActivity).window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {

            userViewModel =
                activity?.run { ViewModelProviders.of(this).get(UserViewModel::class.java) } ?: UserViewModel()
            binding.user = userViewModel.user

            iLoginPresenterInput = LoginPresenter(activity!!, binding.root, fragment)

            iLoginPresenterInput.searchLogo()

            iLoginPresenterInput.searchData()

            login_btn_login.setOnClickListener {
                iLoginPresenterInput.login(userViewModel.user)
            }
        }
    }

    override fun loadLogo(drawable: Drawable) {
        binding.loginImgLogo.setImageDrawable(drawable)
    }

    override fun invalidePassword(mensage: String) {
        binding.loginEditPassword.error = mensage
    }

    override fun invalideLogin(mensage: String) {
        binding.loginEditLogin.error = mensage
    }

    @Test
    override fun resultData(user: User) {
        assertNotNull(user)
        userViewModel.user.login = user.login
        userViewModel.user.password = user.password
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

}
