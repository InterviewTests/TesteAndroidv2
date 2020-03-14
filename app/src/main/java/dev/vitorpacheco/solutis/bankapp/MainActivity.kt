package dev.vitorpacheco.solutis.bankapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewManager = LinearLayoutManager(this)
        viewAdapter = MovimentacoesAdapter(
            arrayOf(
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado",
                "teste",
                "testando",
                "testado"
            )
        )

        recyclerView = lista_movimentacoes.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}

class MovimentacoesAdapter(private val data: Array<String>) :
    RecyclerView.Adapter<MovimentacoesAdapter.MovimentacoesViewHolder>() {

    class MovimentacoesViewHolder(val cardView: MaterialCardView) :
        RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimentacoesViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_statement, parent, false) as MaterialCardView

        return MovimentacoesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovimentacoesViewHolder, position: Int) {
//        holder.cardView.item_movimentacao_label.text = data[position]
    }

    override fun getItemCount() = data.size

}