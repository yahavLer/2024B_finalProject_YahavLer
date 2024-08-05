package com.example.a2024b_finalproject_yahavler.Managers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static void addFavoriteStoreToUser(String storeId) {
        String userId = FirebaseAuth.getInstance().getUid();
        if (userId != null) {
            usersRef.child(userId).child("favoriteStores").child(storeId).setValue(1);        }
    }

    public static void isStoreIsFav(String storeId, CallBack<Boolean> callBack) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersRef.child(userUid).child("favoriteStores").child(storeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String result = snapshot.getValue(String.class);
                callBack.res(result != null && result.equals("1"));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }


    public static void addClubToUser(String clubId) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersRef.child(userUid).child("clubMemberships").child(clubId).setValue("1");
    }
    public static void isClubOfUser(String clubId, CallBack<Boolean> callBack) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
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
        usersRef.child(userUid).child("favoriteStores").child(storeId).removeValue();
    }

    public static void removeClubFromUser(String clubId) {
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersRef.child(userUid).child("clubMemberships").child(clubId).removeValue();
    }


    public static void addClubMembership(ClubMembership membership, String userId, OnSuccessListener<Boolean> listener) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        userRef.child("clubMemberships").child(membership.getClubId()).setValue(membership)
                .addOnCompleteListener(task -> {
                    listener.onSuccess(task.isSuccessful());
                });
    }


    public static void getUserName(String userId, CallBack<String> callBack) {
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
                // Handle error
            }
        });
    }

    public static void getUserClubMemberships(String userId, CallBack<ArrayList<ClubMembership>> callBack) {
        usersRef.child(userId).child("clubMemberships").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ClubMembership> memberships = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ClubMembership membership = dataSnapshot.getValue(ClubMembership.class);
                    if (membership != null) {
                        memberships.add(membership);
                    }
                }
                callBack.res(memberships);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.res(null);
            }
        });
    }
    public static void getClub(String clubId, ClubCallback callback) {
        DatabaseReference clubRef = FirebaseDatabase.getInstance().getReference("clubs").child(clubId);
        clubRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Club club = snapshot.getValue(Club.class);
                if (club != null) {
                    callback.onClubLoaded(club);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

    public interface ClubCallback {
        void onClubLoaded(Club club);
    }
    public static void loadDataFromFirebase() {
        // Load Clubs
        clubsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle data here
                Map<String, Object> clubsData = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    clubsData.put(snapshot.getKey(), snapshot.getValue());
                }
                // Store data locally or update UI
                Log.d("Firebase", "Clubs data loaded: " + clubsData);
                // Example: Toast.makeText(MainActivity.this, "Clubs data loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                Log.e("Firebase", "Failed to load clubs data.", databaseError.toException());
            }
        });

        // Load Stores
        storesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle data here
                Map<String, Object> storesData = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    storesData.put(snapshot.getKey(), snapshot.getValue());
                }
                // Store data locally or update UI
                Log.d("Firebase", "Stores data loaded: " + storesData);
                // Example: Toast.makeText(MainActivity.this, "Stores data loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                Log.e("Firebase", "Failed to load stores data.", databaseError.toException());
            }
        });
    }
}
