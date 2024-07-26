package com.example.a2024b_finalproject_yahavler.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.R;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private List<Store> storeList;

    public StoreAdapter(List<Store> storeList) {
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item_object, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = storeList.get(position);
        holder.storeName.setText(store.getName());
        holder.storeLogo.setImageResource(store.getLogoResId());
        holder.storeBranches.setText("Branches: " + store.getBranches());
        holder.storeLocations.setText("Locations: " + store.getLocations());
        holder.storeClubs.setText("Accepted Clubs: " + store.getAcceptedClubs());

        holder.favoriteButton.setOnClickListener(v -> {
            // Add to favorites logic
        });
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView storeName;
        ImageView storeLogo;
        TextView storeBranches;
        TextView storeLocations;
        TextView storeClubs;
        ImageButton favoriteButton;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.tv_store_name);
            storeLogo = itemView.findViewById(R.id.iv_store_logo);
            storeBranches = itemView.findViewById(R.id.tv_store_branches);
            storeLocations = itemView.findViewById(R.id.tv_store_locations);
            storeClubs = itemView.findViewById(R.id.tv_store_clubs);
            favoriteButton = itemView.findViewById(R.id.movie_IMG_favorite);
        }
    }
}
