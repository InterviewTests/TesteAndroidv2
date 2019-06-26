package com.example.santanderapp.santander.detailScreen.controller;

import com.example.santanderapp.santander.detailScreen.model.ResponseStatement;

public interface IDetailsPresenter {
    void closeProgress();

    void populationScreen(ResponseStatement responseStatement);

}
