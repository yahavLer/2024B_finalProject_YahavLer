package com.example.a2024b_finalproject_yahavler.Managers;

import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppManagerFirebase {
    private FirebaseDatabase database;

    public AppManagerFirebase() {
        database = FirebaseDatabase.getInstance();
    }

    public void addUser(User user) {
        DatabaseReference usersRef = database.getReference("users");
        usersRef.child(user.getUserId()).setValue(user);
    }

    public void addStore(Store store) {
        DatabaseReference storesRef = database.getReference("stores");
        storesRef.child(store.getStoreId()).setValue(store);
    }

    public void addClub(Club club) {
        DatabaseReference clubsRef = database.getReference("clubs");
        clubsRef.child(club.getClubId()).setValue(club);
    }

    public void addStoreToFavorites(String userId, String storeId) {
        DatabaseReference userRef = database.getReference("users").child(userId).child("favoriteStores");
        userRef.child(storeId).setValue(true);
    }

    public void removeStoreFromFavorites(String userId, String storeId) {
        DatabaseReference userRef = database.getReference("users").child(userId).child("favoriteStores");
        userRef.child(storeId).removeValue();
    }

    public void addClubMembership(String userId, ClubMembership membership) {
        DatabaseReference membershipsRef = database.getReference("users").child(userId).child("clubMemberships");
        membershipsRef.child(membership.getClubId()).setValue(membership);
    }

}
