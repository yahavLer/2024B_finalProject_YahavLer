package com.example.a2024b_finalproject_yahavler.Model;

import android.util.Log;

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
        String userId;
        userCounter++;
        userId="U" + userCounter;
        Log.e(userId, "userId "+ userId);
        return userId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public User setUserId() {
        this.userId = generateUserId();
        return this;
}

    public List<ClubMembership> getClubMemberships() {
        return clubMemberships;
    }

    public User setClubMemberships(List<ClubMembership> clubMemberships) {
        this.clubMemberships = clubMemberships;
        return this;
}

    public List<String> getFavoriteStores() {
        return favoriteStores;
    }

    public User setFavoriteStores(List<String> favoriteStores) {
        this.favoriteStores = favoriteStores;
        return this;
    }
}
