package com.example.a2024b_finalproject_yahavler.Model;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private static  int clubCounter= 0;
    private String clubId;
    private String name;
    private List<String> acceptedStores;
    private String logoResId;
    private String cardNumber;
    private String expiryDate;

    public Club() {
    }

    public Club(String name, String logoResId, List<String> acceptedStores, String cardNumber, String expiryDate) {
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

    public Club setName(String name) {
        this.name = name;
        return this;
    }

    public String getClubId() {
        return clubId;
    }

    public Club setClubId(String clubId) {
        this.clubId = clubId;
        return this;
    }

    public List<String> getAcceptedStores() {
        return acceptedStores;
    }

    public Club setAcceptedStores(List<String> acceptedStores) {
        this.acceptedStores = acceptedStores;
        return this;
    }
////////////////////////////////////
    public String getLogoResId() {
        return logoResId;
    }

    public Club setLogoResId(String logoResId) {
        this.logoResId = logoResId;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Club setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public Club setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }
}
