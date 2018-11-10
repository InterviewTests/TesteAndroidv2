package br.com.ibm.teste.android.services.models

import com.google.gson.annotations.SerializedName

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:25
 */
class UserResponse (@SerializedName("user") val user: UserAccount,
                    @SerializedName("error") val error: Error)