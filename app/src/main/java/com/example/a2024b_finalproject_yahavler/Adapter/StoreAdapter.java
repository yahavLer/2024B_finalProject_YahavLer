package com.example.a2024b_finalproject_yahavler.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.Store;
import com.example.a2024b_finalproject_yahavler.R;
import com.example.a2024b_finalproject_yahavler.Callback.StoreCallback;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private Context context;
    private List<Store> storeList;
    private StoreCallback storeCallback;

    public StoreAdapter(List<Store> storeList,Context context) {
        this.storeList = storeList;
        this.context=context;
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
        ImageLoader.init(context);
        ImageLoader.getInstance().load(store.getLogo(), holder.storeLogo);
        holder.storeName.setText(store.getName());
        holder.branchesLocations.setText(String.join("Branches: ", store.getBranchesLocations()));
        holder.storeClubs.setText(String.join("Accepted Clubs: ", store.getAcceptedClubs()));
        if (store.isFavorite())
            holder.favoriteButton.setImageResource(R.drawable.heart);
        else
            holder.favoriteButton.setImageResource(R.drawable.empty_heart);
    }

    @Override
    public int getItemCount() {
        if (storeList==null)
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
            favoriteButton.setOnClickListener(v -> {
                if (storeCallback != null)
                    storeCallback.favoriteButtonClicked(getItem(getAdapterPosition()), getAdapterPosition());
            });
        }
    }
}
