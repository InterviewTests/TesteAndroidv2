package br.com.vinicius.bankapp.ui.home

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.internal.FONT_HELVETICANEUE
import br.com.vinicius.bankapp.internal.FONT_HELVETICANEUELIGHT
import br.com.vinicius.bankapp.internal.Formation
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*

import kotlinx.android.synthetic.main.card_view_statement.*

class HomeAdapterStatement(private val context: Context, private val models: List<StatementModel>)
    : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_statement, parent, false))
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            loadUI(viewHolder)

            loadFonts()
        }
    }

    private fun ViewHolder.loadFonts() {
        val fontLight = Typeface.createFromAsset(context.assets, FONT_HELVETICANEUELIGHT)
        val fontNormal = Typeface.createFromAsset(context.assets, FONT_HELVETICANEUE)
        textViewDateStatement.typeface = fontNormal
        textViewAccountDescription.typeface = fontNormal
        textViewTitle.typeface = fontNormal
        textViewBalanceValue.typeface = fontLight
    }

    private fun ViewHolder.loadUI(viewHolder: ViewHolder) {
        viewHolder.layoutPosition
        textViewDateStatement.text = Formation.stringToStringPattern(models[viewHolder.layoutPosition].date)
        textViewBalanceValue.text = Formation.currencyFormat(models[viewHolder.layoutPosition].value)
        textViewAccountDescription.text = models[viewHolder.layoutPosition].desc
        textViewTitle.text = models[viewHolder.layoutPosition].title
    }

}