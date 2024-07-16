package com.example.a2024b_finalproject_yahavler.Model;

import java.util.List;

public class Club {
    private String name;
    private int logoResId;
    private List<String> acceptedStores;
    private String cardNumber;
    private String expiryDate;

    public Club(String name, int logoResId, List<String> acceptedStores, String cardNumber, String expiryDate) {
        this.name = name;
        this.logoResId = logoResId;
        this.acceptedStores = acceptedStores;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogoResId() {
        return logoResId;
    }

    public void setLogoResId(int logoResId) {
        this.logoResId = logoResId;
    }

    public List<String> getAcceptedStores() {
        return acceptedStores;
    }

    public void setAcceptedStores(List<String> acceptedStores) {
        this.acceptedStores = acceptedStores;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
