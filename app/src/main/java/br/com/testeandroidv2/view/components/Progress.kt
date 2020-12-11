package br.com.testeandroidv2.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

import br.com.testeandroidv2.R

class Progress(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    lateinit var view: View

    init {
        start(context, attrs)
    }

    private fun start(context: Context, attrs: AttributeSet) {
        // seta o Background
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))

        // pega o inflater
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // pega a View
        view = inflater.inflate(R.layout.progress, this)
    }

    fun show() { view.visibility = View.VISIBLE }
    fun hide() { view.visibility = View.GONE    }
}