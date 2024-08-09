package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a2024b_finalproject_yahavler.Adapter.ClubAdapter;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class activity_club_accepted_by_store extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClubAdapter clubAdapter;
    private ArrayList<Club> clubs = new ArrayList<>();
    private TextView storeNameTextView;
    private String storeId;
    private FirebaseUser currentUser;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubs_accepted_by_store);
        findView();
        currentUser=AppManagerFirebase.getCurrentUser();
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        NevigationActivity.findNevigationButtens(this);
        storeId = getIntent().getStringExtra("STORE_ID"); // Get storeId from intent
        fetchStoreData();
        fetchClubsAcceptedByStore();
    }

    private void findView() {
        storeNameTextView = findViewById(R.id.clubs_accepted_by_store_text);
        recyclerView = findViewById(R.id.clubs_recycler_view);
    }

    private void fetchStoreData() {
        DatabaseReference storeRef = FirebaseDatabase.getInstance().getReference("stores").child(storeId);
        storeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String storeName = snapshot.child("name").getValue(String.class);
                    storeNameTextView.setText(storeName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

    private void fetchClubsAcceptedByStore() {
        DatabaseReference storeRef = FirebaseDatabase.getInstance().getReference("stores").child(storeId).child("acceptedClubs");
        storeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clubs.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String clubId = dataSnapshot.getKey();
                    AppManagerFirebase.getClub(clubId, club -> {
                        clubs.add(club);
                        setupRecyclerView();
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

    private void setupRecyclerView() {
        clubAdapter = new ClubAdapter(clubs, this, currentUser.getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clubAdapter);
    }
}
