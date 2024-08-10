package com.example.a2024b_finalproject_yahavler.Model;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private static  int clubCounter= 0;
    private String clubId;
    private String name;
    private List<String> acceptedStores;
    private String logoResId;


    public Club() {
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

    public Club setClubId() {
        this.clubId = generateClubId();
        return this;
    }

    public List<String> getAcceptedStores() {
        return acceptedStores;
    }

    public Club setAcceptedStores(List<String> acceptedStores) {
        this.acceptedStores = acceptedStores;
        return this;
    }
    public String getLogoResId() {
        return logoResId;
    }

    public Club setLogoResId(String logoResId) {
        this.logoResId = logoResId;
        return this;
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubId='" + clubId + '\'' +
                ", name='" + name + '\'' +
                ", acceptedStores=" + acceptedStores +
                ", logoResId='" + logoResId + '\'' +
                '}';
    }
}

