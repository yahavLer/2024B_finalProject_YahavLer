package com.example.a2024b_finalproject_yahavler.ActivityView;

import com.example.a2024b_finalproject_yahavler.Adapter.ClubAdapter;
import com.example.a2024b_finalproject_yahavler.Adapter.UserClubsAdapter;
import com.example.a2024b_finalproject_yahavler.DataManagers.ClubManager;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;

import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;

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
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        findView();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            Log.d("ProfileActivity", "User ID: " + userId);
            loadUserProfile(userId);
            loadUserClubMemberships(userId);
        }

        NevigationActivity.findNevigationButtens(this);
    }

    private void findView() {
        EDT_Hello = findViewById(R.id.Hello);
        EDT_username = findViewById(R.id.profile_EDT_username);
        EDT_email = findViewById(R.id.profile_EDT_email);
        EDT_phone = findViewById(R.id.profile_phone);
        profileImage = findViewById(R.id.profile_image);
        clubsRecyclerView = findViewById(R.id.clubs_recycler_view);
    }

    private void initClubOfUser(){
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

