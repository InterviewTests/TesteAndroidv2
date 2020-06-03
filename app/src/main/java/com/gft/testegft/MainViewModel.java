package com.gft.testegft;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<String> nome = new MutableLiveData<>();

    public MutableLiveData<String> getNome() {
        return nome;
    }

    public void setNome(String novoNome) {
        nome.setValue(novoNome);
    }
}
