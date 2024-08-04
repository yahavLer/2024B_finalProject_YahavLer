package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Adapter.StoreAdapter;
import com.example.a2024b_finalproject_yahavler.DataManagers.StoreManager;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Callback.ObjectCallback;
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
import com.google.gson.Gson;

import java.util.ArrayList;

public class stores_of_club_home_view extends AppCompatActivity implements ObjectCallback {
    private RecyclerView main_LST_store;
    private ArrayList<Store> stores = new ArrayList<>();
    private TextView welcome_text;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores_of_club_home_view);
        findView();
        initAllStores();
        NevigationActivity.findNevigationButtens(this);
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        fetchUserName();
    }
    private void findStore(String store) {

    }

    @Override
    public void onObjectClick(Object object) {

    }

    private void findView() {
        main_LST_store = findViewById(R.id.stores_recycler_view);
        welcome_text = findViewById(R.id.welcome_text); // Initialize the TextView
    }

    private void initAllStores(){
        stores=StoreManager.getStores();
        StoreAdapter storeAdapter = new StoreAdapter(stores, this);
        AppManagerFirebase.addAllStores(stores);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_store.setLayoutManager(linearLayoutManager);
        main_LST_store.setAdapter(storeAdapter);
        storeAdapter.setStoreCallback((store, position) -> {
            store.setFavorite(!store.isFavorite());
            storeAdapter.notifyItemChanged(position);
        });
    }
    private void fetchUserName() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userDatabaseRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    welcome_text.setText("Welcome, " + user.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }
}