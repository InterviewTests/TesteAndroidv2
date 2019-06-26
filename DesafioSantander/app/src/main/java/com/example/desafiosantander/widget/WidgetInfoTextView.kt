package com.example.desafiosantander.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.desafiosantander.R
import com.example.desafiosantander.extensions.withStyledAttributes
import kotlinx.android.synthetic.main.widget_info_text_view.view.title
import kotlinx.android.synthetic.main.widget_info_text_view.view.value

class WidgetInfoTextView(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(
            R.layout.widget_info_text_view,
            this,
            true
        )

        context.withStyledAttributes(
            attrs,
            R.styleable.widget_info_text_view,
            0,
            0
        ) {

            val desc = getResourceId(R.styleable.widget_info_text_view_widget_title, -1)

            setTitle(desc)
        }
    }

    private fun setTitle(desc: Int) {
        if (desc > 0) title.setText(desc)
    }

    fun setValue(content: String) {
        value.text = content
    }
}