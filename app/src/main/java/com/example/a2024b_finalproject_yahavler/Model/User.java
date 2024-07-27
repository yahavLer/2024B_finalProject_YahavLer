package com.example.a2024b_finalproject_yahavler.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int userCounter = 0;
    private String userId = "U0";
    private String name;
    private String phoneNumber;
    private List<ClubMembership> clubMemberships;
    private List<String> favoriteStores;

    public User() {
    }

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.clubMemberships = new ArrayList<>();
        this.favoriteStores = new ArrayList<>();
        this.userId = generateUserId();
    }
    private synchronized String generateUserId() {
        userCounter++;
        return "U" + userCounter;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ClubMembership> getClubMemberships() {
        return clubMemberships;
    }

    public void setClubMemberships(List<ClubMembership> clubMemberships) {
        this.clubMemberships = clubMemberships;
    }

    public List<String> getFavoriteStores() {
        return favoriteStores;
    }

    public void setFavoriteStores(List<String> favoriteStores) {
        this.favoriteStores = favoriteStores;
    }
}
