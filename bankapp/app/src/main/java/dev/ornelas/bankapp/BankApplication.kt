package dev.ornelas.bankapp

import android.app.Application
import dev.ornelas.bankapp.data.DataComponent
import dev.ornelas.bankapp.data.DataContainer

class BankApplication : Application(), AppContainer {

    override val dataComponent: DataComponent
        get() = DataContainer(context = this)

}