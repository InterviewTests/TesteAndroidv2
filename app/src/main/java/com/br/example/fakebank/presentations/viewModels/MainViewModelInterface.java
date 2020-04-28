package com.br.example.fakebank.presentations.viewModels;

public interface MainViewModelInterface {
    Boolean isUserValid(String userName);
    Boolean isPasswordValid(String userPassword);
    void authLogin(String userName, String userPassword);
}
