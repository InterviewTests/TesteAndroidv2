package br.com.testeandroidv2.view

import android.app.Activity
import android.content.DialogInterface
import android.view.KeyEvent

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import br.com.testeandroidv2.R
import br.com.testeandroidv2.utils.UtilsAnimation
import br.com.testeandroidv2.view.listener.OnCallback

abstract class DefaultActivity : AppCompatActivity() {
    private var actionBar: ActionBar? = null
    private var mToolbar: Toolbar? = null

    fun setupToolBar(resource: Int) {
        mToolbar = findViewById(resource)
        setSupportActionBar(mToolbar)
        actionBar = supportActionBar
    }

    fun setActionBarHome()       { actionBar?.setHomeButtonEnabled(true) }
    fun setActionBarHomeButton() { actionBar?.setDisplayHomeAsUpEnabled(true) }

    fun setActionBarNotHome()       { actionBar?.setHomeButtonEnabled(false) }
    fun setActionBarNotHomeButton() { actionBar?.setDisplayHomeAsUpEnabled(false) }

    fun setActionBarTitle(title: String) { actionBar?.title = title }
    fun setActionBarTitle(title: Int)    { actionBar?.title = getString(title) }

    fun setActionBarSubTitle(title: String) { actionBar?.subtitle = title }
    fun setActionBarSubTitle(title: Int)    { actionBar?.subtitle = getString(title) }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            back(Activity.RESULT_CANCELED)
            true
        }
        else {
            super.onKeyDown(keyCode, event)
        }
    }

    fun animLeftToRight() { UtilsAnimation.leftToRight(this) }
    fun animRightToLeft() { UtilsAnimation.rightToLeft(this) }

    fun msgBox(msg: String, callback: OnCallback) {
        val dialog = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            dialog.setMessage(msg)
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK") { dialog1: DialogInterface?, whichButton: Int ->
                callback.onSuccess()
            }
            dialog.create().show()
    }

    abstract fun back(resultCode: Int)
}