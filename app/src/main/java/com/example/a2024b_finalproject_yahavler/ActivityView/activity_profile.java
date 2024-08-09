package com.example.a2024b_finalproject_yahavler.ActivityView;

import com.example.a2024b_finalproject_yahavler.Adapter.StoreAdapter;
import com.example.a2024b_finalproject_yahavler.Adapter.UserClubsAdapter;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_profile extends AppCompatActivity {
    private TextView EDT_Hello;
    private TextView EDT_username;
    private TextView EDT_email;
    private TextView EDT_phone;
    private ImageView profileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private RecyclerView clubsRecyclerView;
    private UserClubsAdapter userClubsAdapter;
    private ArrayList<ClubMembership> clubMembershipsList = new ArrayList<>();
    private RecyclerView storesRecyclerView;
    private StoreAdapter favStoreOfUserAdapter ;
    private ArrayList<Store> favStoreList = new ArrayList<>();
    private FirebaseUser currentUser;
    private Button btnFavorite;
    private Button btnClubs;
    private String curUserIdFire;
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        findView();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        currentUser = AppManagerFirebase.getCurrentUser();
        if (currentUser != null) {
            curUserIdFire = currentUser.getUid();
            Log.d("ProfileActivity", "User ID: " + curUserIdFire);
            loadUserProfile(curUserIdFire);
            loadUserClubMemberships(curUserIdFire);
            loadUserFavStore(curUserIdFire);
        }

        NevigationActivity.findNevigationButtens(this);
        initAdapters();

        btnFavorite.setOnClickListener(v -> showFavoriteStores());
        btnClubs.setOnClickListener(v -> showUserClubs());
    }

    private void initAdapters() {
        if (favStoreOfUserAdapter == null) {
            initFavStoreOfUser();
        } else {
            favStoreOfUserAdapter.notifyDataSetChanged();
        }
        if (userClubsAdapter == null) {
            initClubOfUser();
        } else {
            userClubsAdapter.notifyDataSetChanged();
        }
    }

    private void findView() {
        EDT_Hello = findViewById(R.id.Hello);
        EDT_username = findViewById(R.id.profile_EDT_username);
        EDT_email = findViewById(R.id.profile_EDT_email);
        EDT_phone = findViewById(R.id.profile_phone);
        profileImage = findViewById(R.id.profile_image);
        clubsRecyclerView = findViewById(R.id.UserClub_recycler_view);
        storesRecyclerView = findViewById(R.id.favorites_recycler_view);
        btnFavorite = findViewById(R.id.btn_favorite);
        btnClubs = findViewById(R.id.btn_clubs);
    }
    private void showFavoriteStores() {
        clubsRecyclerView.setVisibility(View.INVISIBLE);
        storesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showUserClubs() {
        clubsRecyclerView.setVisibility(View.VISIBLE);
        storesRecyclerView.setVisibility(View.INVISIBLE);
    }


    private void initFavStoreOfUser(){
        storesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favStoreOfUserAdapter = new StoreAdapter(favStoreList, this, currentUser.getUid());
        storesRecyclerView.setAdapter(favStoreOfUserAdapter);
    }

    private void loadUserFavStore(String userId) {
        AppManagerFirebase.fetchUserFavoriteStores(userId, favoriteStores -> {
            if (favoriteStores != null) {
                Log.d("ProfileActivity", "Fetching favorite stores.");
                favStoreList.clear();
                favStoreList.addAll(favoriteStores);
                if (favStoreOfUserAdapter == null) {
                    initFavStoreOfUser();
                } else {
                    favStoreOfUserAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initClubOfUser() {
        clubsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userClubsAdapter = new UserClubsAdapter(clubMembershipsList);
        clubsRecyclerView.setAdapter(userClubsAdapter);
    }

    private void loadUserClubMemberships(String userId) {
        AppManagerFirebase.getUserClubMemberships(userId, memberships -> {
            if (memberships != null) {
                clubMembershipsList.clear();
                clubMembershipsList.addAll(memberships);
                if (userClubsAdapter == null) {
                    initClubOfUser();
                } else {
                    userClubsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loadUserProfile(String userId) {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    EDT_Hello.setText("Hello " + user.getUsername());
                    EDT_username.setText(user.getUsername());
                    EDT_email.setText(user.getEmail());
                    EDT_phone.setText(user.getPhone());
                    // Load profile image if needed
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause called");
        // Save any necessary state here
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy called");
        // Clean up any resources here
    }
}