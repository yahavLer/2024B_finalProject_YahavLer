package com.example.a2024b_finalproject_yahavler.Managers;

import androidx.annotation.NonNull;

import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppManagerFirebase {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private static DatabaseReference clubsRef = database.getReference("clubs");
    private static DatabaseReference usersRef = database.getReference("users");
    private static DatabaseReference storesRef = database.getReference("stores");

    public static FirebaseDatabase getDatabase() {
        return database;
    }

    public static DatabaseReference getClubsRef() {
        return clubsRef;
    }

    public static DatabaseReference getUsersRef() {
        return usersRef;
    }

    public static DatabaseReference getStoresRef() {
        return storesRef;
    }
    public interface CallBack<T> {
        void res(T res);
    }

    public static void addUser(User user) {
        usersRef.child(user.getUserId()).setValue(user);
    }

    public static void addClub(Club club) {
        clubsRef.child(club.getClubId()).setValue(club);
    }
    public static void addAllStores(ArrayList<Store>allStores){
        storesRef.setValue(allStores);
    }
    public static void addAllClubs(ArrayList<Club>allClubs){
        clubsRef.setValue(allClubs);
    }

    public static void addFavoriteStoreToUser(String storeId){
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();;
        DatabaseReference usersRef = database.getReference("users");
        usersRef.child(userUid).child("favoriteStores").child(storeId).setValue(1);
    }

    public static void isStoreIsFav(String storeId, CallBack<Boolean> callBack) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userUid).child("favoriteStores").child(storeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer result = snapshot.getValue(Integer.class);

                if (result != null  &&   result > 0) {
                    callBack.res(true);
                } else{
                    callBack.res(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static void addClubToUser(String clubId) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userUid).child("clubMemberships").child(clubId).setValue(1);
    }
    public static void isClubOfUser(String clubId, CallBack<Boolean> callBack) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userUid).child("clubMemberships").child(clubId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer result = snapshot.getValue(Integer.class);

                if (result != null  &&   result > 0) {
                    callBack.res(true);
                } else{
                    callBack.res(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void removeStoreFromFavorites(String storeId) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userUid).child("favoriteStores").child(storeId).removeValue();
    }

    public static void removeClubFromUser(String clubId) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userUid).child("clubMemberships").child(clubId).removeValue();
    }


    public static void addClubMembership(String membershipId) {
//        DatabaseReference membershipsRef = database.getReference("users").child(userId).child("clubMemberships");
//        membershipsRef.child(membershipId.getClubId()).setValue(membershipId);
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userUid).child("clubOfUser").child(membershipId).setValue(1);

    }
    public static void getUserName(String userId, CallBack<String> callBack) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null) {
                    callBack.res(user.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
