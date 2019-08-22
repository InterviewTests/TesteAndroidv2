package br.com.giovanni.testebank.Activity;

import java.util.List;

import br.com.giovanni.testebank.Model.TransactionDetail;

public interface IPresenterActivity {

    void getResponse(List<TransactionDetail> getList);
}