package br.com.mdr.testeandroid.extensions

import br.com.mdr.testeandroid.R
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * @author Marlon D. Rocha
 * @since 05/07/20
 */

private const val snackSideMargin = 16

fun Context.showErrorSnack(view: View, msg: String): Snackbar? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        val textId = com.google.android.material.R.id.snackbar_text
        val snackView = snackBar.view
        val txtSnack = snackView.findViewById<TextView>(textId)
        val params = snackView.layoutParams as FrameLayout.LayoutParams

        params.setMargins(
            params.leftMargin + snackSideMargin,
            params.topMargin,
            params.rightMargin + snackSideMargin,
            params.bottomMargin + snackSideMargin
        )
        snackView.layoutParams = params
        snackView.setBackgroundResource(R.drawable.error_snack_corner)

        txtSnack.setTextColor(ContextCompat.getColor(this, R.color.white))
        return snackBar.apply {
            show()
        }
    } else {
        showToast(msg)
        return null
    }
}

fun Context.showSuccessSnack(view: View, msg: String): Snackbar? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        val textId = com.google.android.material.R.id.snackbar_text
        val snackView = snackBar.view
        val txtSnack = snackView.findViewById<TextView>(textId)
        val params = snackView.layoutParams as FrameLayout.LayoutParams

        params.setMargins(
            params.leftMargin + snackSideMargin,
            params.topMargin,
            params.rightMargin + snackSideMargin,
            params.bottomMargin + snackSideMargin
        )
        snackView.layoutParams = params
        snackView.setBackgroundResource(R.drawable.bg_success_snack)

        txtSnack.setTextColor(ContextCompat.getColor(this, R.color.white))
        return snackBar.apply {
            show()
        }
    } else {
        showToast(msg)
        return null
    }
}

private fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.stringResourceByName(idName: String): String? {
    return try {
        val resId: Int = resources.getIdentifier(idName, "string", packageName)
        getString(resId)
    } catch (e: Throwable) {
        null
    }
}
