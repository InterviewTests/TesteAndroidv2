package br.com.alex.bankappchallenge.feature.statement

sealed class StatementIntentions {
    object FetchUserAccountData : StatementIntentions()
    object FetchStatements : StatementIntentions()
}