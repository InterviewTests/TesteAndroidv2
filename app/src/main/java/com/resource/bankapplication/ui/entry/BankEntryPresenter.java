package com.resource.bankapplication.ui.entry;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.data.repository.StatementsRepository;
import com.resource.bankapplication.domain.Spent;

import java.util.List;

public class BankEntryPresenter implements BankEntryContract.Presenter {

    private BankEntryContract.View view;

    public BankEntryPresenter(BankEntryContract.View view) {
        this.view = view;
    }

    @Override
    public void loadList(Long idUser) {
        Spent spent = new Spent();
        spent.setRepository(new StatementsRepository());
        spent.listSpent(idUser, new BaseCallback<List<Spent>>() {
            @Override
            public void onSuccessful(List<Spent> value) {
                view.listSpent(value);
            }

            @Override
            public void onUnsuccessful(String error) {
                view.showError(error);
            }
        });
    }
}
