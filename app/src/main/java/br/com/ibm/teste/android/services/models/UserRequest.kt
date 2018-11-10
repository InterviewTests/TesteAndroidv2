package br.com.ibm.teste.android.services.models

import com.google.gson.annotations.SerializedName

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:32
 */
class UserRequest (@SerializedName("user") val user: String,
                   @SerializedName("password") val password: String)