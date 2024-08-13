package com.example.a2024b_finalproject_yahavler.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class UserClubsAdapter extends RecyclerView.Adapter<UserClubsAdapter.ViewHolder> {

    private ArrayList<ClubMembership> clubMembershipsList;

    public UserClubsAdapter(ArrayList<ClubMembership> clubMembershipsList) {
        this.clubMembershipsList = clubMembershipsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClubMembership clubMembership = clubMembershipsList.get(position);
        holder.TV_club_name.setText(clubMembership.getClubId());
        holder.TV_club_card_number.setText("Card Number: " + clubMembership.getCreditCardInfo());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        String expiryDateStr = sdf.format(clubMembership.getMembershipExpiry());
        holder.TV_club_expiry_date.setText("Expiry Date: " + expiryDateStr);
        // Fetch Club details for logo
        AppManagerFirebase.fetchClubById(clubMembership.getClubId(), club -> {
            if (club != null) {
                holder.TV_club_name.setText(club.getName());
                ImageLoader.getInstance().load(club.getLogoResId(), holder.clubLogo);
            } else {
                // הגדר ערכים ברירת מחדל במקרה שהמועדון לא נמצא
                holder.TV_club_name.setText("Club not found");
                holder.clubLogo.setImageResource(R.drawable.unavailable_photo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubMembershipsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView TV_club_name, TV_club_card_number, TV_club_expiry_date, TV_club_accepted_stores;
        ImageView clubLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clubLogo = itemView.findViewById(R.id.IV_club_logo);
            TV_club_name = itemView.findViewById(R.id.TV_club_name);
            TV_club_card_number = itemView.findViewById(R.id.TV_club_card_number);
            TV_club_expiry_date = itemView.findViewById(R.id.TV_club_expiry_date);
            TV_club_accepted_stores = itemView.findViewById(R.id.TV_club_accepted_stores);
        }
    }
}
