package com.example.a2024b_finalproject_yahavler.Model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static  int storeCounter= 0;
    private String storeId;
    private String name;
    private List<String> locations;
    private List<String> acceptedClubs;
    private boolean isFavorite;
    private int logoResId;
    private List<String> branches;


    public Store() {
    }

    public Store(int logoResId,List<String> branches,String name) {
        this.name = name;
        this.locations = new ArrayList<>();
        this.acceptedClubs = new ArrayList<>();
        this.isFavorite = false;
        this.storeId = generateStoreId();
        this.logoResId = logoResId;
        this.branches = branches;
    }
    private synchronized String generateStoreId() {
        storeCounter++;
        return "S" + storeCounter;
    }
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getAcceptedClubs() {
        return acceptedClubs;
    }

    public void setAcceptedClubs(List<String> acceptedClubs) {
        this.acceptedClubs = acceptedClubs;
    }

    public int getLogoResId() {
        return logoResId;
    }

    public void setLogoResId(int logoResId) {
        this.logoResId = logoResId;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }
}


