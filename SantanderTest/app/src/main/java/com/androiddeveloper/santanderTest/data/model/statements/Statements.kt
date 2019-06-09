package com.androiddeveloper.santanderTest.data.model.statements

data class Statements(var statementList: List<Item>)

data class Item(
    var title: String,
    var desc: String,
    var date: String,
    var value: Double
)

data class ItemDTO(
    var title: String = "",
    var desc: String = "",
    var date: String = "",
    var value: String = ""
)