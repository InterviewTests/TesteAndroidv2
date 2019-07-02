package com.example.santandertestebank.ui;

import com.example.santandertestebank.model.models.ObjectsStatements;

public class BankPaymentsContract {

    public interface View {

        void showToast(String s);

        void showUserinfos(ObjectsStatements objectsStatements);

        void logoutApp();

    }

    public interface Presenter {

        void bringStatements(long id);

    }
}
