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

    private HashMap<String, Integer> clubMemberships = new HashMap<>();
    private HashMap<String, Integer> favoriteStores = new HashMap<>();


    public User() {
    }

    public User(String userId, String username, String email, String phone) {
        this.username = username;
        this.phone = phone;
        this.userId = userId;
        this.email=email;
    }
    private synchronized String generateUserId() {
        String userId;
        userCounter++;
        userId="U" + userCounter;
        Log.e(userId, "userId "+ userId);
        return userId;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User setFirebaseUserId(String id) {
        this.firebaseUid = generateUserId();
        return this;
    }
    public HashMap<String, Integer> getClubMemberships() {
        return clubMemberships;
    }

    public void setClubMemberships(HashMap<String, Integer> clubMemberships) {
        this.clubMemberships = clubMemberships;
    }

    public HashMap<String, Integer> getFavoriteStores() {
        return favoriteStores;
    }

    public void setFavorites(HashMap<String, Integer> favoriteStores) {
        this.favoriteStores = favoriteStores;
    }

    public User addFavorite(String storeId) {
        this.favoriteStores.put(storeId, 1);
        return this;
    }


    public User addClubMemberships(String ClubId) {
        this.clubMemberships.put(ClubId, 1);
        return this;
    }

}
