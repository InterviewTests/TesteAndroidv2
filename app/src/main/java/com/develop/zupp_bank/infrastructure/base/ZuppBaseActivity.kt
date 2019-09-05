package com.develop.zupp_bank.infrastructure.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.develop.zupp_bank.R

open class ZuppBaseActivity<T : AppCompatActivity> : AppCompatActivity() {
    private lateinit var activity: T

    lateinit var navController: NavController
    lateinit var toolbar: Toolbar

    val fragmentActivity: FragmentActivity
        get() = this

    val context: Context
        get() = this

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this as T
    }

    fun navigate(idRes: Int, bundle: Bundle) {
        val navOp = NavOptions.Builder().setEnterAnim(R.anim.enter_slide_in_right)
            .setExitAnim(R.anim.exit_slide_out_left)
            .setPopEnterAnim(R.anim.pop_enter_slide_in_left)
            .setPopExitAnim(R.anim.pop_exit_slide_out_right).build()
        navController.navigate(idRes, bundle, navOp)
    }

    fun navigate(idRes: Int) {
        navigate(idRes, Bundle())
    }

    fun getNavigate(): NavController {
        return navController
    }

    fun hideToolBar() {
        toolbar.visibility = View.GONE
    }

    fun showToolBar() {
        toolbar.visibility = View.VISIBLE
    }

    fun showToast(msg: String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }

}