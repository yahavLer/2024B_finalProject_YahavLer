package com.example.a2024b_finalproject_yahavler.Model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static  int storeCounter= 0;
    private String storeId;
    private String name;
    private ArrayList<String> branchesLocations = null;
    private ArrayList<String> acceptedClubs= null;
    private boolean isFavorite = false;
    private String logo="";


    public Store() {
    }

    private synchronized String generateStoreId() {
        storeCounter++;
        return "S" + storeCounter;
    }
    public boolean isFavorite() {
        return isFavorite;
    }

    public Store setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    public String getName() {
        return name;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }

    public String getStoreId() {
        return storeId;
    }

    public Store setStoreId() {
        this.storeId = generateStoreId();
        return this;
    }

    public ArrayList<String> getBranchesLocations() {
        return branchesLocations;
    }

    public Store setBranchesLocations(ArrayList<String> branchesLocations) {
        this.branchesLocations = branchesLocations;
        return this;
    }

    public ArrayList<String> getAcceptedClubs() {
        return acceptedClubs;
    }

    public Store setAcceptedClubs(ArrayList<String> acceptedClubs) {
        this.acceptedClubs = acceptedClubs;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Store setLogo(String logo) {
        this.logo = logo;
        return this;
    }
}


