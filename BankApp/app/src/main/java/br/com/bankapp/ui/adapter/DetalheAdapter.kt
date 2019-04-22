package br.com.bankapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.bankapp.R
import br.com.bankapp.model.DetalheLogin
import kotlinx.android.synthetic.main.card_view_detalhe_login.view.*
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*




class DetalheAdapter(private val context: Context, private var detalheList: MutableList<DetalheLogin>):
    RecyclerView.Adapter<DetalheAdapter.DetalheViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DetalheViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_detalhe_login, parent, false)
        return DetalheViewHolder(view)
    }

    override fun getItemCount(): Int {
        return detalheList.size
    }

    override fun onBindViewHolder(holder: DetalheViewHolder, position: Int) {
        holder.bindView(detalheList[position])
    }


    class DetalheViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewPagamento = itemView.tvPagamento
        val textViewConta = itemView.tvConta
        val textViewData = itemView.tvData
        val textaViewValor = itemView.tvValor

        fun bindView(detalheLogin: DetalheLogin) {
            textViewPagamento.text = detalheLogin.title
            textViewConta.text = detalheLogin.desc

            var dateOriginal = detalheLogin.date
            var dataFormatada = "${dateOriginal?.substring(8,10)}/${dateOriginal?.substring(5,7)}/${dateOriginal?.substring(0,4)}"

            textViewData.text = dataFormatada

            val decFormat = DecimalFormat("'R$ ' #,###,##0.00")
            textaViewValor.text = decFormat.format(detalheLogin.value)
        }
    }
}