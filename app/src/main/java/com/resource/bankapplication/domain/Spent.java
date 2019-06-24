package com.resource.bankapplication.domain;

import com.resource.bankapplication.ConstantsApp;
import com.resource.bankapplication.config.BaseCallback;

import java.util.List;

public class Spent {

    private SpentContract.IRepository repository;
    private String typeTransaction;
    private String description;
    private String date;
    private Double value;

    public Spent() { }

    public Spent(String typeTransaction, String description, String date, Double value) {
        this.typeTransaction = typeTransaction;
        this.description = description;
        this.date = date;
        this.value = value;
    }

    public void setRepository(SpentContract.IRepository repository) {
        this.repository = repository;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }
    public void listSpent(long idUser, BaseCallback<List<Spent>> onResult){

        if(repository==null){
            onResult.onUnsuccessful(ConstantsApp.REPOSITORY_NULL);
            return;
        }
        if(idUser<=0){
            onResult.onUnsuccessful(ConstantsApp.ID_INVALID);
            return;
        }
        repository.listStatements(idUser, new BaseCallback<List<Spent>>() {
            @Override
            public void onSuccessful(List<Spent> value) {
                onResult.onSuccessful(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                onResult.onUnsuccessful(error);
            }
        });
    }
}
