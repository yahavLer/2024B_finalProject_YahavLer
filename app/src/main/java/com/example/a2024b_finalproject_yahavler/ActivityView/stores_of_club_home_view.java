package com.example.a2024b_finalproject_yahavler.ActivityView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Adapter.StoreAdapter;
import com.example.a2024b_finalproject_yahavler.Callback.CustomBackCallback;
import com.example.a2024b_finalproject_yahavler.DataManagers.StoreManager;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Callback.ObjectCallback;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class stores_of_club_home_view extends AppCompatActivity implements ObjectCallback {
    private RecyclerView main_LST_store;
    private ArrayList<Store> stores = new ArrayList<>();
    private ArrayList<Store> filteredStores = new ArrayList<>();
    private FirebaseUser currentUser;
    private TextView welcome_text;
    private DatabaseReference userDatabaseRef;
    private SearchView search_text;
    private Button search_button;
    private StoreAdapter storeAdapter;
    private MaterialButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores_of_club_home_view);
        findView();
        btnLogout.setOnClickListener(v -> logoutUser());
        CustomBackCallback callback = new CustomBackCallback(this);
        getOnBackPressedDispatcher().addCallback(this, callback);
        currentUser=AppManagerFirebase.getCurrentUser();
        setupSearchFunctionality();
        NevigationActivity.findNevigationButtens(this);
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        fetchUserName();
        initAllStores();
    }
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "logged out succefully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void setupSearchFunctionality() {
        search_text.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchStores(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchStores(newText);
                return true;
            }
        });
    }

    private void searchStores(String query) {
        filteredStores.clear();
        if (query.isEmpty()) {
            filteredStores.addAll(stores);
        } else {
            for (Store store : stores) {
                if (store.getName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredStores.add(store);
                }
            }
        }
        storeAdapter.notifyDataSetChanged();
    }

    private void findView() {
        main_LST_store = findViewById(R.id.stores_recycler_view);
        welcome_text = findViewById(R.id.welcome_text);
        search_text = findViewById(R.id.search_text);
        search_button = findViewById(R.id.search_button);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void initAllStores() {
        AppManagerFirebase.fetchAllStores(new AppManagerFirebase.CallBack<ArrayList<Store>>() {
            @Override
            public void res(ArrayList<Store> allStores) {
                if (allStores != null) {
                    stores.clear();
                    stores.addAll(allStores);
                    filteredStores.clear();
                    filteredStores.addAll(stores);
                    storeAdapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_store.setLayoutManager(linearLayoutManager);
        storeAdapter = new StoreAdapter(filteredStores, this, currentUser.getUid());
        main_LST_store.setAdapter(storeAdapter);
    }

    private void fetchUserName() {
        if (currentUser != null) {
            String userId = currentUser.getUid();
            AppManagerFirebase.fetchUserName(userId, new AppManagerFirebase.CallBack<String>() {
                @Override
                public void res(String username) {
                    if (username != null) {
                        welcome_text.setText(username);
                    } else {
                        Log.d("username null", "username");
                    }
                }
            });
        }
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
    @Override
    public void onObjectClick(Object object) {

    }
}
