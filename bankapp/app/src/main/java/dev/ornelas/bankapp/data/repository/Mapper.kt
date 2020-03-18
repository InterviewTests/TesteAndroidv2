package dev.ornelas.bankapp.data.repository

import android.annotation.SuppressLint
import dev.ornelas.banckapp.domain.model.Mapper
import dev.ornelas.banckapp.domain.model.Statement
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.LoginResponseApi
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.StatementApi
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
var simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

object ApiToUserModel : Mapper<LoginResponseApi, User> {
    override fun map(type: LoginResponseApi): User {
        return User(
            id = type.userAccount.userId,
            name = type.userAccount.name,
            agency = type.userAccount.agency.toString(),
            balance = type.userAccount.balance,
            bankAccount = type.userAccount.bankAccount.toString()
        )
    }
}

fun LoginResponseApi.toUserModel() = ApiToUserModel.map(this)

object ApiToStatementModel : Mapper<StatementApi, Statement> {
    override fun map(type: StatementApi): Statement {
        val date = simpleDateFormat.parse(type.date)

        return Statement(
            title = type.title,
            desc = type.desc,
            date = date,
            value = type.value
        )

    }
}

fun StatementApi.toStatementModel() = ApiToStatementModel.map(this)