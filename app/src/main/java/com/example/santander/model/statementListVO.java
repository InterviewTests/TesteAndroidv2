package com.example.santander.model;

import com.squareup.moshi.Json;

import java.util.List;

public class statementListVO {

    @Json(name = "statementList")
    private List<statementVO> statementList;

}
