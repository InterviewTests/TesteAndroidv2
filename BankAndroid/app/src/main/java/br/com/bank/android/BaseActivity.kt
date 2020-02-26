package br.com.bank.android

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.bank.android.exceptions.BusinessError
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.loading.*

abstract class BaseActivity : AppCompatActivity() {

    private var loadingView: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLoadingView()
    }

    private fun setLoadingView() {
        this.loadingView = loading
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (this.loadingView?.visibility == View.VISIBLE) return true
        return super.dispatchTouchEvent(ev)
    }

    fun setShowLoading(visible: Boolean) {
        loadingView?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun showErrorDialog(error: BusinessError) {
        val message = error.getMessage(this)
        MaterialAlertDialogBuilder(this).setMessage(message)
            .setPositiveButton(android.R.string.ok, null).show()
    }

    fun showError(textInputLayout: TextInputLayout, message: String) {
        textInputLayout.error = message
        textInputLayout.requestFocus()
        showKeyboard(textInputLayout.editText)
    }

    fun showKeyboard(editText: EditText?) {
        editText?.let {
            val imm = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager?
            imm?.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}