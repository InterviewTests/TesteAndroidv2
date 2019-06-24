package com.resource.bankapplication.domain;

import com.resource.bankapplication.config.BaseCallback;

import java.util.List;

public class SpentContract {
    public interface IRepository{
        void listStatements(long idUser, BaseCallback<List<Spent>> onResult);
    }
}
