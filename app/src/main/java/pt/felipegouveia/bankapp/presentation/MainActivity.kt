package pt.felipegouveia.bankapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import pt.felipegouveia.bankapp.util.ConnectionLiveData

class MainActivity : AppCompatActivity() {

    lateinit var connectionLiveData: ConnectionLiveData
    var networkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectionLiveData = ConnectionLiveData(this)

        connectionLiveData.observe(this, Observer {
            networkAvailable = it
        })

    }
}
