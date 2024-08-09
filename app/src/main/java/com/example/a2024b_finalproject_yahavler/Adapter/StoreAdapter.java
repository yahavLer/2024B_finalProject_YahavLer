package com.example.a2024b_finalproject_yahavler.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;
import com.example.a2024b_finalproject_yahavler.Callback.StoreCallback;
import com.example.a2024b_finalproject_yahavler.ActivityView.activity_club_accepted_by_store;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.HashMap;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private Context context;
    private List<Store> storeList;
    private StoreCallback storeCallback;
    private String curUserIdFire;
    private HashMap<String, Integer> favoriteStoreIds = new HashMap<>();
    private User user = new User();
    public StoreAdapter(List<Store> storeList, Context context, String curUserIdFire) {
        this.storeList = storeList;
        this.context = context;
        this.curUserIdFire =curUserIdFire;
        fetchUserFavoriteStores();
    }
    private void fetchUserFavoriteStores() {
        AppManagerFirebase.fetchUserById(curUserIdFire, fetchedUser -> {
            if (fetchedUser != null) {
                this.user = fetchedUser;
                this.favoriteStoreIds = user.getFavoriteStores();
                notifyDataSetChanged(); // Notify the adapter to refresh data
            }
        });
    }
    public void setStoreCallback(StoreCallback storeCallback) {
        this.storeCallback = storeCallback;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item_object, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = getItem(position);
        setupStoreDetails(holder, store);
        setupFavoriteButton(holder, store);
        setupClickListeners(holder, store, position);
    }

    private void setupStoreDetails(@NonNull StoreViewHolder holder, Store store) {
        ImageLoader.init(context);
        ImageLoader.getInstance().loadImage(store.getLogo(), holder.storeLogo);
        holder.storeName.setText(store.getName());
        holder.branchesLocations.setText(String.join("Branches: ", store.getBranchesLocations()));
        holder.storeClubs.setText(String.join("Accepted Clubs: ", store.getAcceptedClubs()));
    }

    private void setupFavoriteButton(@NonNull StoreViewHolder holder, Store store) {
        if (favoriteStoreIds.containsKey(store.getStoreId()))
            holder.favoriteButton.setImageResource(R.drawable.heart);
        else
            holder.favoriteButton.setImageResource(R.drawable.empty_heart);
    }

    private void setupClickListeners(@NonNull StoreViewHolder holder, Store store, int position) {
        holder.favoriteButton.setOnClickListener(v -> {
            if (storeCallback != null) {
                storeCallback.favoriteButtonClicked(store, position);
            }
            if (favoriteStoreIds.containsKey(store.getStoreId())) {
                // Remove from favorites
                AppManagerFirebase.removeStoreFromFavorites(store.getStoreId());
                favoriteStoreIds.remove(store.getStoreId());
            } else {
                // Add to favorites
                AppManagerFirebase.addFavoriteStoreToUser(store.getStoreId());
                favoriteStoreIds.put(store.getStoreId(), 1); // Add to local map
            }
            store.setFavorite(!store.isFavorite());
            notifyItemChanged(position); // Update the item view
        });
        holder.itemView.setOnClickListener(v -> {
            if (storeCallback != null) {
                storeCallback.onStoreClick(store);
            }
            Intent intent = new Intent(context, activity_club_accepted_by_store.class);
            intent.putExtra("STORE_ID", store.getStoreId());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        if (storeList == null)
            return 0;
        else
            return storeList.size();
    }

    public Store getItem(int position) {
        return storeList.get(position);
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        private final CardView store_CARD_data;
        private final TextView storeName;
        private final ImageView storeLogo;
        private final TextView branchesLocations;
        private final TextView storeClubs;
        private final ShapeableImageView favoriteButton;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            store_CARD_data = itemView.findViewById(R.id.store_CARD_data);
            storeName = itemView.findViewById(R.id.tv_store_name);
            storeLogo = itemView.findViewById(R.id.iv_store_logo);
            branchesLocations = itemView.findViewById(R.id.tv_store_branches_locations);
            storeClubs = itemView.findViewById(R.id.tv_store_clubs);
            favoriteButton = itemView.findViewById(R.id.movie_IMG_favorite);
        }
    }
}
