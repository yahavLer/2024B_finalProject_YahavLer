package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Adapter.StoreAdapter;
import com.example.a2024b_finalproject_yahavler.DataManagers.StoreManager;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Callback.ObjectCallback;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.R;

import com.google.gson.Gson;

import java.util.ArrayList;

public class stores_of_club_home_view extends AppCompatActivity implements ObjectCallback {
    private RecyclerView main_LST_store;
    private ArrayList<Store> stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores_of_club_home_view);
        findView();
        initAllStores();
        NevigationActivity.findNevigationButtens(this);

    }
    private void findStore(String store) {

    }

    @Override
    public void onObjectClick(Object object) {

    }

    private void findView() {
        main_LST_store = findViewById(R.id.stores_recycler_view);
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

}