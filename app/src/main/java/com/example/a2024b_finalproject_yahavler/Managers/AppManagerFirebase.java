package com.example.a2024b_finalproject_yahavler.Managers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a2024b_finalproject_yahavler.Callback.StoreCallback;
import com.example.a2024b_finalproject_yahavler.DataManagers.ClubManager;
import com.example.a2024b_finalproject_yahavler.DataManagers.StoreManager;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppManagerFirebase {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private static DatabaseReference clubsRef = database.getReference("clubs");
    private static DatabaseReference usersRef = database.getReference("users");
    private static DatabaseReference storesRef = database.getReference("stores");
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseUser currentUser = mAuth.getCurrentUser();

    public static FirebaseUser getCurrentUser() {
        return currentUser;
    }

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

    public static void addFavoriteStoreToUser(String storeId, String userId) {
        DatabaseReference userRef = usersRef.child(userId);
        userRef.child("favoriteStores").child(storeId).setValue(1);
    }

    public static void addClubMembership(ClubMembership membership, String userId, OnSuccessListener<Boolean> listener) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        userRef.child("clubMemberships").child(membership.getClubId()).setValue(membership)
                .addOnCompleteListener(task -> {
                    listener.onSuccess(task.isSuccessful());
                });
    }
    public static void fetchFavoriteStores(ArrayList<String> favStoreIdList, CallBack<ArrayList<Store>> callback) {
        ArrayList<Store> allFavStores = new ArrayList<>();
        storesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot storeSnapshot : dataSnapshot.getChildren()) {
                    Store store = storeSnapshot.getValue(Store.class);
                    Log.d("initUserFavStores", "favoriteStores: " + store.getStoreId());
                    if (store != null && favStoreIdList.contains(store.getStoreId())) {
                        allFavStores.add(store);
                    }
                }
                // החזרת הרשימה של החנויות דרך הקולבק
                callback.res(allFavStores);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // טיפול בשגיאה במקרה של ביטול או כישלון
            }
        });
    }
    public static void fetchUserFavoriteStores(String userId, CallBack<ArrayList<Store>> callback) {
        DatabaseReference userRef = usersRef.child(userId);
        DatabaseReference favUserRef = userRef.child("favoriteStores");
        favUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Store> allStores = new ArrayList<>();
                for (DataSnapshot storeSnapshot : snapshot.getChildren()) {
                    String favStoreId = storeSnapshot.getKey();
                    fetchStoreById(favStoreId,fetchedStore -> {
                        if (fetchedStore != null) {
                            Store favStore = new Store();
                            favStore = fetchedStore;
                            allStores.add(favStore);
                        }
                    });
                }
                callback.res(allStores);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.res(null);
            }
        });
    }

    public static void fetchFavoriteStores(String userId, StoreCallback callback) {
        DatabaseReference userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(userId)
                .child("favoriteStores");

        userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> favoriteStores = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    favoriteStores.add(dataSnapshot.getValue(String.class));
                }
                callback.onFavoriteStoresFetched(favoriteStores); // קריאה למתודה החדשה
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

    private static void fetchStoreById(String storeId, CallBack<Store> callback) {
        storesRef.child(storeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Store store = snapshot.getValue(Store.class);
                callback.res(store);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.res(null);
            }
        });
    }

    public static void fetchUserById(String userId, CallBack<User> callback) {
        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                callback.res(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.res(null);
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

    public static void fetchUserName(String userId, CallBack<String> callBack) {
        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    callBack.res(user.getUsername());
                } else {
                    callBack.res(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.res(null);
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

    public static void fetchAllStores(CallBack<ArrayList<Store>> callback) {
        storesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Store> allStores = new ArrayList<>();
                for (DataSnapshot storeSnapshot : snapshot.getChildren()) {
                    Store store = storeSnapshot.getValue(Store.class);
                    if (store != null) {
                        allStores.add(store);
                    }
                }
                callback.res(allStores);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.res(null);
            }
        });
    }

    public static void fetchAllClubs(CallBack<ArrayList<Club>> callback) {
        clubsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Club> allClubs = new ArrayList<>();
                for (DataSnapshot clubSnapshot : snapshot.getChildren()) {
                    Club club = clubSnapshot.getValue(Club.class);
                    if (club != null) {
                        allClubs.add(club);
                    }
                }
                callback.res(allClubs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.res(null);
            }
        });
    }

    public static void clearFirebaseData() {
        storesRef.removeValue();
        clubsRef.removeValue();
    }

    public static void initStoresFirebaseData() {
        storesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    // Data doesn't exist, upload it
                    addAllStores(StoreManager.getStores());
                } else {
                    Log.d("Firebase", "Data already exists, skipping initialization.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    public static void initClubsFirebaseData() {
        clubsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    // Data doesn't exist, upload it
                    addAllClubs(ClubManager.getClub());
                } else {
                    Log.d("Firebase", "Clubs data already exists, skipping initialization.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

}
