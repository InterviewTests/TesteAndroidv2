package com.example.santandertestebank.model.models;

public class ObjectsStatments {

    private PaymentsStatmentList paymentsStatmentList;

    private Error errorStatment;

    public PaymentsStatmentList getPaymentsStatmentList() {
        return paymentsStatmentList;
    }

    public void setPaymentsStatmentList(PaymentsStatmentList paymentsStatmentList) {
        this.paymentsStatmentList = paymentsStatmentList;
    }

    public Error getErrorStatment() {
        return errorStatment;
    }

    public void setErrorStatment(Error errorStatment) {
        this.errorStatment = errorStatment;
    }
}
