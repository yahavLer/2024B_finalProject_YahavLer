package com.example.a2024b_finalproject_yahavler.Model;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private static  int clubCounter= 0;
    private String clubId;
    private String name;
    private List<String> acceptedStores;
    private int logoResId;
    private String cardNumber;
    private String expiryDate;

    public Club() {
    }

    public Club(String name, int logoResId, List<String> acceptedStores, String cardNumber, String expiryDate) {
        this.name = name;
        this.acceptedStores = new ArrayList<>();
        this.clubId = generateClubId();
    }
    private synchronized String generateClubId() {
        clubCounter++;
        return "C" + clubCounter;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public List<String> getAcceptedStores() {
        return acceptedStores;
    }

    public void setAcceptedStores(List<String> acceptedStores) {
        this.acceptedStores = acceptedStores;
    }
////////////////////////////////////
    public int getLogoResId() {
        return logoResId;
    }

    public void setLogoResId(int logoResId) {
        this.logoResId = logoResId;
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
