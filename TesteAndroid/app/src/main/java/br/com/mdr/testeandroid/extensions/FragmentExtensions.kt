package br.com.mdr.testeandroid.extensions

import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.mdr.testeandroid.R
import com.google.android.material.snackbar.Snackbar

/**
 * @author Marlon D. Rocha
 * @since 08/07/20
 */

fun Fragment.showErrorSnack(snackMessage: String) {
    val snackBar = Snackbar.make(this.requireView(), snackMessage, Snackbar.LENGTH_LONG)
    val textId = com.google.android.material.R.id.snackbar_text
    val snackView = snackBar.view
    val txtSnack = snackView.findViewById<TextView>(textId)
    txtSnack.maxLines = 5
    val params = snackView.layoutParams as FrameLayout.LayoutParams
    val sideMargin = 16
    params.setMargins(params.leftMargin + sideMargin,
        params.topMargin,
        params.rightMargin + sideMargin,
        params.bottomMargin + sideMargin)
    snackView.layoutParams = params
    snackView.setBackgroundResource(R.drawable.error_snack_corner)

    txtSnack.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
    snackBar.show()
}