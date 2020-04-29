package com.br.example.fakebank.presentations.viewModels;

public interface LoginViewModelInterface {
    Boolean isUserValid(String userName);
    Boolean isPasswordValid(String userPassword);
    void authLogin(String userName, String userPassword);
}
