package com.example.vamosjogarv1.model;
import android.util.Log;
import android.webkit.JavascriptInterface;


import java.util.Observable;
import java.util.Observer;

public class CreditCard {
    private String cardNumber;
    private String name;
    private String month;
    private String year;
    private String cvv;


    public CreditCard(){

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}