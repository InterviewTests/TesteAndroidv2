package com.projeto.testedevandroidsantander.ui.mainScreen;

import com.projeto.santander.model.LancamentoModel;
import com.projeto.santander.model.LancamentoViewModel;
import com.projeto.santander.model.UsuarioModel;

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