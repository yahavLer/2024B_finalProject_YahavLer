package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Adapter.StoreAdapter;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class activity_store_accepted_by_club extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView clubNameTextView;

    private String clubId;
    private FirebaseUser currentUser;

    private StoreAdapter storeAdapter;

    private ArrayList<Store> storesAcceptByClub = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores_accepted_by_club);
        findView();
        NevigationActivity.findNevigationButtens(this);
        currentUser=AppManagerFirebase.getCurrentUser();

        clubId = getIntent().getStringExtra("CLUB_ID"); // מקבל את ה-clubId מה-intent
        fetchClubData(clubId);
    }

    private void findView() {
        clubNameTextView = findViewById(R.id.stores_accepted_by_club_text);
        recyclerView = findViewById(R.id.stores_recycler_view);
    }

    private void fetchClubData(String clubId) {
        AppManagerFirebase.fetchClubById(clubId, fetchClub -> {
            if (fetchClub != null){
                clubNameTextView.setText("Stores accepting: " + fetchClub.getName());
                fetchStoresAcceptedByClub(fetchClub);
            }
        });
    }

    private void fetchStoresAcceptedByClub(Club club) {
        AppManagerFirebase.fetchStoresAcceptedByClub(clubId, stores -> {
            if (stores != null) {
                storesAcceptByClub.addAll(stores);
                initStoresRecyclerView();
            }
        });
    }

    private void initStoresRecyclerView() {
        if (storeAdapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            storeAdapter = new StoreAdapter(storesAcceptByClub, this, currentUser.getUid());
            recyclerView.setAdapter(storeAdapter);
        } else {
            storeAdapter.notifyDataSetChanged();
        }
    }
}
