package br.com.testeandroidv2.utils.http

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class HttpResponse(var codeHttp: Int = 0,
                        var codeError: Int = 0,
                        var message: String? = null,
                        var status: String? = null,
                        var timeFinish: Long = 0,
                        var messageResponse: String? = null) : Parcelable