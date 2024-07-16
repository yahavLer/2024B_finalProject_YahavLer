package com.example.a2024b_finalproject_yahavler.Model;

import java.util.List;

public class Store {
    private String name;
    private int logoResId;
    private List<String> branches;
    private List<String> locations;
    private List<String> acceptedClubs;

    public Store(String name, int logoResId, List<String> branches, List<String> locations, List<String> acceptedClubs) {
        this.name = name;
        this.logoResId = logoResId;
        this.branches = branches;
        this.locations = locations;
        this.acceptedClubs = acceptedClubs;
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

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
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
}
