package com.projeto.testedevandroidsantander.ui.mainScreen;

import com.projeto.testedevandroidsantander.model.LancamentoModel;
import com.projeto.testedevandroidsantander.model.LancamentoViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

interface MainPresenterInput {
    void presentMainMetaData(MainResponse response);
    void visibleProgressBar();
    void hideProgressBar();
}

public class MainPresenter implements MainPresenterInput {

    public WeakReference<MainActivityInput> output;

    @Override
    public void presentMainMetaData(MainResponse response) {
        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.lancamentos = new ArrayList<>();

        if (response.lancamentos != null) {
            for (LancamentoModel lm : response.lancamentos) {
                LancamentoViewModel lvm = new LancamentoViewModel();
                lvm.titulo = lm.titulo;
                lvm.descricao = lm.descricao;
                lvm.valor = lm.valor;
                lvm.data = lm.data;

                mainViewModel.lancamentos.add(lvm);
            }

            output.get().displayMainMetaData(mainViewModel);
        }
    }

    @Override
    public void visibleProgressBar() {
        output.get().showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        output.get().hideProgressBar();
    }
}
