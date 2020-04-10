package pt.felipegouveia.bankapp.data.login.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import pt.felipegouveia.bankapp.BR

class LoginBody(
    user: String? = "",
    password: String? = ""
) : BaseObservable(){

    @get:Bindable
    var user : String? = user
        set(value) {
            field = value
            notifyPropertyChanged(BR.user)
        }

    @get:Bindable
    var password : String? = password
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}
