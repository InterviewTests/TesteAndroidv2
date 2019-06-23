package com.resource.bankapplication.ui.entry;

import com.resource.bankapplication.domain.Spent;

import java.util.List;

public class BankEntryContract {

    interface View{

        void listSpent(List<Spent> value);

        void showError(String error);

        void showProgress(boolean show);
    }
    interface Presenter{

        void loadList(Long idUser);
    }
}
