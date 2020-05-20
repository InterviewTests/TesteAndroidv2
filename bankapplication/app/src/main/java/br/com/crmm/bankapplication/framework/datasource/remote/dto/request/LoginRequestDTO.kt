package br.com.crmm.bankapplication.framework.datasource.remote.dto.request

data class LoginRequestDTO(
    var user: String = "",
    var password: String = ""
)