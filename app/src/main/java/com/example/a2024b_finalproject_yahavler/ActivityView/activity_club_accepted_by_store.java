package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a2024b_finalproject_yahavler.Adapter.ClubAdapter;
import com.example.a2024b_finalproject_yahavler.Adapter.StoreAdapter;
import com.example.a2024b_finalproject_yahavler.Adapter.UserClubsAdapter;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
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
    private UserClubsAdapter userClubsAdapter;
    private FirebaseUser currentUser;
    private User user = new User();
    private ArrayList<ClubMembership> clubMembershipsList = new ArrayList<>();
    private String curUserIdFire;
    private Store store = new Store();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubs_accepted_by_store);
        findView();
        currentUser = AppManagerFirebase.getCurrentUser();
        if (currentUser != null) {
            curUserIdFire = currentUser.getUid();
        }
        NevigationActivity.findNevigationButtens(this);
        storeId = getIntent().getStringExtra("STORE_ID"); // Get storeId from intent
        fetchStoreData(storeId);
        fetchClubsAcceptedByStore();
    }

    private void findView() {
        storeNameTextView = findViewById(R.id.clubs_accepted_by_store_text);
        recyclerView = findViewById(R.id.clubs_recycler_view);
    }

    private void fetchStoreData(String storeId) {
        AppManagerFirebase.fetchStoreById(storeId, fetchStore -> {
            if (fetchStore != null){
                store = fetchStore;
            }
        });
    }

    private void fetchClubsAcceptedByStore() {
        AppManagerFirebase.fetchClubsAcceptedByStore(storeId,new AppManagerFirebase.CallBack<ArrayList<Club>>() {
            @Override
            public void res(ArrayList<Club> allClubs) {
                if (allClubs != null) {
                    clubs.addAll(allClubs);
                    Log.d("fetchClubsAcceptedByStore", "ClubsAcceptedByStore: " + clubs.toString());
                    clubAdapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        clubAdapter = new ClubAdapter(clubs, this, currentUser.getUid());
        recyclerView.setAdapter(clubAdapter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause called");
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
    }
}

