package com.gft.testegft;

import androidx.lifecycle.MutableLiveData;

import com.gft.testegft.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<String> user = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<String> getUser() {
        return user;
    }

    public void setUser(String newUser) {
        user.setValue(newUser);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }
}
