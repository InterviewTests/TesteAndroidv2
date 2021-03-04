package com.example.accentureprojectbank

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtratoActivity: AppCompatActivity() {
    private var listStatementsItems: ArrayList<Informacoes> = arrayListOf()

    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.extrato_activitiy)

        val identificationUser = intent.getIntExtra("id_user", 1)
        val identificationCount = intent.getStringExtra("count_user")
        val identificationAg = intent.getStringExtra("ag_user")
        val identificationValue = intent.getDoubleExtra("value_user", 1.1)
        val identificationName = intent.getStringExtra("nome_user")

        val colocarNome = findViewById<TextView>(R.id.nome_usuario)
        colocarNome.text = identificationName.toString()

        val colocarAgAndConta = findViewById<TextView>(R.id.ag_conta)
        colocarAgAndConta.text = identificationCount.toString() + " / " + identificationAg.toString()

        val colocarSaldo = findViewById<TextView>(R.id.saldo)
        colocarSaldo.text = "R$ $identificationValue"


        val retornar = findViewById<ImageView>(R.id.img)
        retornar.setOnClickListener {
            onBackPressed()
        }


        fazerSegundaChamada(identificationUser)

    }

    private fun fazerSegundaChamada(idUser: Int) {

        val retrofitClient = Api.getRetrofitInstance()
        val endpoint = retrofitClient.create(Api::class.java)
        val callback = endpoint.dadosTransacoes(idUser)

        callback.enqueue(object : Callback<StatementsResponse> {
            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {
                val a = "deu erro"
            }

            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                response.body()?.let {
                    val x = it
                    listStatementsItems = x.inf
                    montarLista()

                }
            }
        })

    }

    private fun montarLista(){

        val recycler by lazy { findViewById<RecyclerView>(R.id.recycler_view) }
        val receipts = listStatementsItems

        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(receipts as MutableList<Informacoes>)
        recycler.adapter = adapter

    }

    private fun getRecibos(): List<Recibos> {
        val listaRecibosOk = mutableListOf<Recibos>()

        listaRecibosOk.add(Recibos("Pagamento 1", "Conta de Água", "01/01/2021", "R$50,00"))
        listaRecibosOk.add(Recibos("Pagamento 2", "Conta de energia", "01/02/2021", "R$100,00"))
        listaRecibosOk.add(Recibos("Pagamento 3", "Conta de gás", "10/02/2021", "R$80,00"))
        listaRecibosOk.add(Recibos("Pagamento 4", "Compra 1", "15/02/2021", "R$150,00"))
        listaRecibosOk.add(Recibos("Pagamento 5", "Compra 2", "18/02/2021", "R$180,00"))
        listaRecibosOk.add(Recibos("Pagamento 6", "Compra 3", "20/02/2021", "R$200,00"))

        return listaRecibosOk

    }



}


