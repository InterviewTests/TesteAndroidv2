package br.com.crmm.bankapplication.data.source.remote.dto.request

data class LoginRequestDTO(
    var user: String = "",
    var password: String = ""
)