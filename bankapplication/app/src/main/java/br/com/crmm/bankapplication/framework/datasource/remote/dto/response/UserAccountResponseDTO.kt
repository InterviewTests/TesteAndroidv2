package br.com.crmm.bankapplication.framework.datasource.remote.dto.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserAccountResponseDTO(
    @SerializedName("userId")
    val userId: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("bankAccount")
    val bankAccount: String? = "",
    @SerializedName("agency")
    val agency: String? = "",
    @SerializedName("balance")
    val balance: Double? = 0.0
): Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(name)
        parcel.writeString(bankAccount)
        parcel.writeString(agency)
        parcel.writeValue(balance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserAccountResponseDTO> {
        override fun createFromParcel(parcel: Parcel): UserAccountResponseDTO {
            return UserAccountResponseDTO(parcel)
        }

        override fun newArray(size: Int): Array<UserAccountResponseDTO?> {
            return arrayOfNulls(size)
        }
    }

}