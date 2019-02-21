package com.avanade.testesantander2.homeScreen;

import com.avanade.testesantander2.CurrencyResponse;
import com.avanade.testesantander2.Statement;
import com.avanade.testesantander2.UserAccount;

import java.util.ArrayList;

public class HomeModel {
}

class HomeViewModel{
    // Response da API
    // LoginResponse[UserAccount userAccount, Erro error]
    UserAccount userAccount;
    ArrayList<Statement> currencyAccount;
}

class HomeRequest{
    int idUser;
}

class HomeResponse{
    CurrencyResponse apiCallCurrencyResponse;
}