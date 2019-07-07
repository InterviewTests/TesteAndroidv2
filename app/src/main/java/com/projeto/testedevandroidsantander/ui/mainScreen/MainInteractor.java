package com.projeto.testedevandroidsantander.ui.mainScreen;


import com.projeto.testedevandroidsantander.model.LancamentoDTO;

interface MainInteractorInput {
    void fetchMainMetaData(MainRequest request);
    void presentMainMetaData(LancamentoDTO lancamentoDTO);
}

public class MainInteractor implements MainInteractorInput {

    public MainPresenterInput output;

    public LancamentoWorkerInput lancamentoWorkerInput;

    public static String TAG = MainInteractor.class.getSimpleName();

    @Override
    public void fetchMainMetaData(MainRequest request) {
        output.visibleProgressBar();
        lancamentoWorkerInput.getLancamentosByIdUser(request.usuarioModel.id.intValue());
    }

    @Override
    public void presentMainMetaData(LancamentoDTO lancamentoDTO){
        MainResponse mainResponse = new MainResponse();
        mainResponse.lancamentos =  lancamentoDTO.getStatementList();
        output.presentMainMetaData(mainResponse);
        output.hideProgressBar();
    }
}
