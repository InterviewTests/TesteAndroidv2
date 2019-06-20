package br.com.vinicius.bankapp.domain

class Statement (
    override val date: String,
    override val desc: String,
    override val title: String,
    override val value: Double
) :StatementContract.IStatement{

}