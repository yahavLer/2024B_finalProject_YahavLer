package com.example.a2024b_finalproject_yahavler.Model;

import android.util.Log;

import java.util.HashMap;

public class User {
    private static int userCounter = 0;
    private String userId = "U0";
    private String firebaseUid;
    private String username;
    private String phone;
    private String email;

    private HashMap<String, ClubMembership> clubMemberships = new HashMap<>();
    private HashMap<String, String> favoriteStores = new HashMap<>();

    public User() {
    }

    private synchronized String generateUserId() {
        userCounter++;
        return "U" + userCounter;
    }
    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public User setUserId() {
        this.userId = generateUserId();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }


    public User setFirebaseUserId(String id) {
        this.firebaseUid = generateUserId();
        return this;
    }
    public HashMap<String, ClubMembership> getClubMemberships() {
        return clubMemberships;
    }

    public void setClubMemberships(HashMap<String, ClubMembership> clubMemberships) {
        this.clubMemberships = clubMemberships;
    }

    public HashMap<String, String> getFavoriteStores() {
        return favoriteStores;
    }

    public void setFavorites(HashMap<String, String> favoriteStores) {
        this.favoriteStores = favoriteStores;
    }

    public User addFavorite(String storeId) {
        this.favoriteStores.put(storeId, "1");
        return this;
    }

    public void addClubMembership(ClubMembership membership) {
        clubMemberships.put(membership.getClubId(), membership);
    }

}
