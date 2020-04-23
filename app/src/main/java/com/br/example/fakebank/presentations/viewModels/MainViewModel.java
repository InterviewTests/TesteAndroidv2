package com.br.example.fakebank.presentations.viewModels;

import androidx.lifecycle.ViewModel;

import com.br.example.fakebank.presentations.handles.MainHandle;
import com.br.example.fakebank.domains.models.MainModel;

public class MainViewModel extends ViewModel {

    private MainModel mainModel;
    private MainHandle mainHandle;

    public MainViewModel(MainModel mainModel, MainHandle mainHandle) {
        this.mainModel = mainModel;
        this.mainHandle = mainHandle;
    }
}
