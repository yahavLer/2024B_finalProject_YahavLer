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
    private TextView EDT_Hello, EDT_username, EDT_email, EDT_phone;
    private ImageView profileImage;
    private RecyclerView clubsRecyclerView, storesRecyclerView;
    private Button btnFavorite,btnClubs ;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private User user = new User();

    private UserClubsAdapter userClubsAdapter;
    private StoreAdapter favStoreOfUserAdapter ;

    private ArrayList<ClubMembership> clubMembershipsList = new ArrayList<>();
    private ArrayList<Store> favStoreList = new ArrayList<>();
    private ArrayList<String> favStoreIdList ;

    private String curUserIdFire;

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
            loadUserProfile();

        }

        NevigationActivity.findNevigationButtens(this);

        btnFavorite.setOnClickListener(v -> showFavoriteStores());
        btnClubs.setOnClickListener(v -> showUserClubs());
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

    private void initUserFavStores(ArrayList<String>favStoreIdList) {
        AppManagerFirebase.fetchFavoriteStores(favStoreIdList, new AppManagerFirebase.CallBack<ArrayList<Store>>() {
            @Override
            public void res(ArrayList<Store> favoriteStores) {
                if (favoriteStores != null) {
                    Log.d("initUserFavStores", "favoriteStores: " + favoriteStores.toString());
                    favStoreList.addAll(favoriteStores);
                    favStoreOfUserAdapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        storesRecyclerView.setLayoutManager(linearLayoutManager);
        favStoreOfUserAdapter = new StoreAdapter(favStoreList, this, currentUser.getUid());
        storesRecyclerView.setAdapter(favStoreOfUserAdapter);
    }


    private void initClubOfUser() {
        if (userClubsAdapter == null) {
            Log.d("initClubOfUser", "clubMembershipsList: " + clubMembershipsList.toString());
            clubsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            userClubsAdapter = new UserClubsAdapter(clubMembershipsList);
            clubsRecyclerView.setAdapter(userClubsAdapter);
        } else {
            userClubsAdapter.notifyDataSetChanged();
        }
    }

    private void loadUserClubMemberships(String userId) {
        AppManagerFirebase.getUserClubMemberships(userId, memberships -> {
            if (memberships != null) {
                clubMembershipsList.clear();
                clubMembershipsList.addAll(memberships);
                Log.d("loadUserClubMemberships", "clubMembershipsList: " + clubMembershipsList.toString());
                if (userClubsAdapter == null) {
                    initClubOfUser();
                } else {
                    userClubsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loadUserProfile() {
        AppManagerFirebase.fetchUserById(curUserIdFire, fetchedUser -> {
            if (fetchedUser != null) {
                User user1 = new User();
                user1=fetchedUser;
                ArrayList<String> arr ;
                arr =new ArrayList<>(user1.getFavoriteStores().keySet());
                this.favStoreIdList =new ArrayList<>(user1.getFavoriteStores().keySet());
                Log.d("loadUserProfile", "favStoreList: " + arr.toString());
                EDT_Hello.setText("Hello " + user1.getUsername());
                EDT_username.setText(user1.getUsername());
                EDT_email.setText(user1.getEmail());
                EDT_phone.setText(user1.getPhone());
                loadUserClubMemberships(curUserIdFire);
                initUserFavStores(arr);
                // Load profile image if needed
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