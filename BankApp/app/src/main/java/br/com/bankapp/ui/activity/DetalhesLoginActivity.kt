package br.com.bankapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import br.com.bankapp.R
import br.com.bankapp.data.DetalheLoginDelegate
import br.com.bankapp.data.DetalheLoginRes
import br.com.bankapp.data.DetalheLoginTask
import br.com.bankapp.model.Usuario
import br.com.bankapp.ui.adapter.DetalheAdapter
import br.com.bankapp.util.Alert
import kotlinx.android.synthetic.main.content_detalhe.*
import java.net.HttpURLConnection
import java.text.DecimalFormat

class DetalhesLoginActivity : AppCompatActivity(), DetalheLoginDelegate {


    private var usuario: Usuario? = null
    lateinit var detalheAdapter: DetalheAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_login)
        setToolbar()
        getParametros()
        carregarInfo()
    }

    private fun setToolbar() {
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getParametros() {
        val extras = intent.extras
        if (extras != null) {
            usuario = extras.getSerializable("PARAM_USER") as Usuario
            doDetalheLogin()
        }
    }

    private fun carregarInfo(){
        txtNome.text = usuario?.name
        txtConta.text = "${usuario?.bankAccount}/${usuario?.agency}"
        val decFormat = DecimalFormat("'R$ ' #,###,##0.00")
        txtSaldo.text = decFormat.format(usuario?.balance)
    }

    private fun doDetalheLogin(){
        DetalheLoginTask(this, this).execute(usuario?.userId)
    }

    override fun onDetalheLoginResult(detalheLoginRes: DetalheLoginRes) {
        if (detalheLoginRes != null) {
            if (detalheLoginRes.statusCode == HttpURLConnection.HTTP_OK) {
                detalheAdapter = DetalheAdapter(this, detalheLoginRes.listDetalheLogin!!)
                recyclerViewDetalhes.adapter = detalheAdapter
                recyclerViewDetalhes.layoutManager = LinearLayoutManager(this)
                recyclerViewDetalhes.smoothScrollToPosition(detalheLoginRes.listDetalheLogin!!.size)
            }else{
                Alert.alertSimples(this, detalheLoginRes.mensagem.toString())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}
