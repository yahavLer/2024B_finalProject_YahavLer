package com.example.a2024b_finalproject_yahavler.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.R;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    private List<Club> clubList;

    public ClubAdapter(List<Club> clubList) {
        this.clubList = clubList;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_details_item, parent, false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = clubList.get(position);
        ImageLoader.getInstance().load(club.getLogoResId(), holder.clubLogo);
        holder.clubName.setText(club.getName());
        holder.clubStores.setText("Accepted Stores: " + club.getAcceptedStores());
        holder.cardNumber.setText("Card Number: " + club.getCardNumber());
        holder.cardExpiry.setText("Expiry Date: " + club.getExpiryDate());
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {

        TextView clubName;
        ImageView clubLogo;
        TextView clubStores;
        TextView cardNumber;
        TextView cardExpiry;

        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            clubName = itemView.findViewById(R.id.TV_club_name);
            clubLogo = itemView.findViewById(R.id.IV_club_logo);
            clubStores = itemView.findViewById(R.id.TV_club_accepted_stores);
            cardNumber = itemView.findViewById(R.id.TV_club_card_number);
            cardExpiry = itemView.findViewById(R.id.TV_club_expiry_date);
        }
    }
}
