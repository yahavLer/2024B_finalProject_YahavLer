package com.example.a2024b_finalproject_yahavler.Managers;

import android.content.Context;

import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AppManagerFirebase {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private static DatabaseReference clubsRef = database.getReference("clubs");
    private static DatabaseReference usersRef = database.getReference("users");
    private static DatabaseReference storesRef = database.getReference("stores");

    public static void addUser(User user) {
        usersRef.child(user.getUserId()).setValue(user);
    }

    public static void addClub(Club club) {
        clubsRef.child(club.getClubId()).setValue(club);
    }
    public static void addAllStores(ArrayList<Store>allStores){
        storesRef.setValue(allStores);
    }

    public static void addStoreToFavorites(String userId, String storeId) {
        DatabaseReference userRef = database.getReference("users").child(userId).child("favoriteStores");
        userRef.child(storeId).setValue(true);
    }

    public static void removeStoreFromFavorites(String userId, String storeId) {
        DatabaseReference userRef = database.getReference("users").child(userId).child("favoriteStores");
        userRef.child(storeId).removeValue();
    }

    public static void addClubMembership(String userId, ClubMembership membership) {
        DatabaseReference membershipsRef = database.getReference("users").child(userId).child("clubMemberships");
        membershipsRef.child(membership.getClubId()).setValue(membership);
    }

}
