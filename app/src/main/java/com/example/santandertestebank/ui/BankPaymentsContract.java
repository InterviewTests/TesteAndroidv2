package com.example.santandertestebank.ui;

import android.content.Context;

import com.example.santandertestebank.model.models.ObjectsStatements;

public class BankPaymentsContract {

    public interface View {

        Context getContext();

        void showToast(String s);

        void showUserinfos(ObjectsStatements objectsStatements);

        void logoutApp();

    }

    public interface Presenter {

        void bringStatements(long id);

        void validateId(long id) throws Exception;

    }
}
