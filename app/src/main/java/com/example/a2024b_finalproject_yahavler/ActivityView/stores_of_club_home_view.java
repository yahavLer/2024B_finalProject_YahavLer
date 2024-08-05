package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;
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
import java.util.HashSet;
import java.util.Set;

public class stores_of_club_home_view extends AppCompatActivity implements ObjectCallback {
    private RecyclerView main_LST_store;
    private ArrayList<Store> stores = new ArrayList<>();
    private ArrayList<Store> filteredStores = new ArrayList<>();
    private Set<String> userStoreIds = new HashSet<>();

    private TextView welcome_text;
    private DatabaseReference userDatabaseRef;
    private SearchView search_text;
    private Button search_button;
    private StoreAdapter storeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores_of_club_home_view);
        findView();
        initAllStores();
        setupSearchFunctionality();  // Add this method call
        NevigationActivity.findNevigationButtens(this);
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        fetchUserName();
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
    }

    private void initAllStores(){
        stores = StoreManager.getStores();
        filteredStores.addAll(stores);
//        storeAdapter = new StoreAdapter(stores, this);
        storeAdapter = new StoreAdapter(filteredStores, this);
        AppManagerFirebase.addAllStores(stores);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_store.setLayoutManager(linearLayoutManager);
        main_LST_store.setAdapter(storeAdapter);
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
