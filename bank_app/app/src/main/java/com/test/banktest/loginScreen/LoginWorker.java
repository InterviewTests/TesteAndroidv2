package com.test.banktest.loginScreen;

interface LoginWorkerInput {
    //Define needed interfaces
    public boolean validateUser(String email);
    public boolean validatePassword(String password);
}

public class LoginWorker implements LoginWorkerInput {

    public boolean validateUser(String email){
        return true;
    }

    public boolean validatePassword(String password){
        return true;
    }

}
