package com.example.santandertestebank.model;

public class Payments {

    private String paymentTitle;
    private String paymentDescription;
    private String paymentDate;
    private double paymentValue;

    public Payments(String payment, String paymentType, String paymentDate, double paymentValue) {
        this.paymentTitle = payment;
        this.paymentDescription = paymentType;
        this.paymentDate = paymentDate;
        this.paymentValue = paymentValue;
    }

    public Payments() {
    }

    public String getPaymentTitle() {
        return paymentTitle;
    }

    public void setPaymentTitle(String paymentTitle) {
        this.paymentTitle = paymentTitle;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(double paymentValue) {
        this.paymentValue = paymentValue;
    }
}