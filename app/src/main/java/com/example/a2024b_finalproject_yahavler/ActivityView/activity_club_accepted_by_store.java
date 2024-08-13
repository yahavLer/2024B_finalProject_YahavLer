package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a2024b_finalproject_yahavler.Adapter.ClubAdapter;
import com.example.a2024b_finalproject_yahavler.Adapter.UserClubsAdapter;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class activity_club_accepted_by_store extends AppCompatActivity {
    private FirebaseUser currentUser;
    private User user = new User();
    private Store store = new Store();

    private RecyclerView recyclerView;
    private TextView storeNameTextView;

    private String curUserIdFire;
    private String storeId;

    private ClubAdapter clubAdapter;
    private UserClubsAdapter userClubsAdapter;

    private ArrayList<Club> clubsAcceptByStore = new ArrayList<>();
    private ArrayList<ClubMembership> clubsMemberOfUserList = new ArrayList<>();
    private ArrayList<ClubMembership> clubsMemUserByStore = new ArrayList<>();

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
        Log.d("store id", storeId);
        fetchStoreData(storeId);
        Log.d("store_data_fatch", store.toString());
    }

    private void findView() {
        storeNameTextView = findViewById(R.id.clubs_accepted_by_store_text);
        recyclerView = findViewById(R.id.clubs_recycler_view);
    }

    private void fetchStoreData(String storeId) {
        AppManagerFirebase.fetchStoreById(storeId, fetchStore -> {
            if (fetchStore != null){
                this.store = fetchStore;
                Log.d("store_data", store.toString());
                storeNameTextView.setText("Your club accepted by: " + store.getName());
                fetchClubsAcceptedByStore();
            }
        });
    }

    private void fetchClubsAcceptedByStore() {
        AppManagerFirebase.fetchClubsAcceptedByStore(storeId,allClubs -> {
            if (allClubs != null) {
                clubsAcceptByStore.addAll(allClubs);
                Log.d("ClubsAcceptedByStore", clubsAcceptByStore.toString());
                fetchUserClubMemberships(clubsAcceptByStore,curUserIdFire );
//                userClubsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initClubOfUser() {
        if (userClubsAdapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            userClubsAdapter = new UserClubsAdapter(clubsMemUserByStore, this, curUserIdFire);
            recyclerView.setAdapter(userClubsAdapter);
        } else {
            userClubsAdapter.notifyDataSetChanged();
        }
    }

    private void fetchUserClubMemberships(ArrayList<Club> clubs, String UserIdFire) {
        AppManagerFirebase.getUserClubMemberships(UserIdFire, clubMemberships -> {
            if (clubMemberships != null) {
                clubsMemberOfUserList.clear();
                clubsMemberOfUserList.addAll(clubMemberships);
                Log.d("fetchUserClubMemberships", clubsMemberOfUserList.toString());
                checkClubsMemberships(clubs, clubsMemberOfUserList);
                if (userClubsAdapter == null) {
                    initClubOfUser();
                } else {
                    userClubsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void checkClubsMemberships(ArrayList<Club> clubs, ArrayList<ClubMembership> clubMemberships) {
        for (Club club : clubs) {
            for (ClubMembership membership : clubMemberships) {
                if (membership.getClubId().equals(club.getClubId())) {
                    Log.d("MatchFound", "User is a member of club: " + club.getName());
                    clubsMemUserByStore.add(membership);
                }
            }
        }
        Log.d("checkClubsMemberships", clubsMemUserByStore.toString());
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

