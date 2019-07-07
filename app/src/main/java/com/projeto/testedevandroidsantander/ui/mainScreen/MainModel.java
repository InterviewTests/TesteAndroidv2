package com.projeto.testedevandroidsantander.ui.mainScreen;

import com.projeto.testedevandroidsantander.model.LancamentoModel;
import com.projeto.testedevandroidsantander.model.LancamentoViewModel;
import com.projeto.testedevandroidsantander.model.UsuarioModel;

import java.util.List;

public class MainModel {
}

class MainViewModel {
    List<LancamentoViewModel> lancamentos;
}

class MainRequest{
    UsuarioModel usuarioModel;
}

class MainResponse {
    List<LancamentoModel> lancamentos;
}