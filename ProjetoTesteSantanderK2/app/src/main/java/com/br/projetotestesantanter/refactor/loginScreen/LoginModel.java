package com.br.projetotestesantanter.refactor.loginScreen;

import com.google.gson.annotations.SerializedName;

public class LoginModel {


    public static class LoginResponse {

        @SerializedName("userId")
        private Long userId;
        @SerializedName("name")
        private String name;
        @SerializedName("bankAccount")
        private String bankAccount;
        @SerializedName("agency")
        private String agency;

        @SerializedName("balance")
        private float balance;

        @SerializedName("error")
        private Error error;

        class Error {

        }

        public Long getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public String getAgency() {
            return agency;
        }

        public float getBalance() {
            return balance;
        }

        public Error getError() {
            return error;
        }

        public LoginResponse() {

        }
    }

    public static class LoginRequest {

        String login;
        String password;
    }

}
