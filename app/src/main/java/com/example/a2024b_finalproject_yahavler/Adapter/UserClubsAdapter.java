package com.example.a2024b_finalproject_yahavler.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.ActivityView.activity_store_accepted_by_club;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class UserClubsAdapter extends RecyclerView.Adapter<UserClubsAdapter.ViewHolder> {

    private ArrayList<ClubMembership> clubMembershipsList;
    private Context context;
    private User user = new User();
    private String curUserIdFire;

    public UserClubsAdapter(ArrayList<ClubMembership> clubMembershipsList, Context context, String curUserIdFire) {
        this.clubMembershipsList = clubMembershipsList;
        this.context = context;
        this.curUserIdFire =curUserIdFire;
        fetchUser();
    }

    private void fetchUser() {
        AppManagerFirebase.fetchUserById(curUserIdFire, fetchedUser -> {
            if (fetchedUser != null) {
                this.user = fetchedUser;
                notifyDataSetChanged(); // Notify the adapter to refresh data
            }
        });
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
        setupClickListeners(holder, clubMembership, position);
    }

    @Override
    public int getItemCount() {
        return clubMembershipsList.size();
    }
    private void setupClickListeners(@NonNull ViewHolder holder, ClubMembership clubMembership,  int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, activity_store_accepted_by_club.class);
            intent.putExtra("CLUB_ID", clubMembership.getClubId());
            context.startActivity(intent);
        });
        holder.removeClub_IV.setOnClickListener(v ->{
            new AlertDialog.Builder(context)
                    .setTitle("מחיקת מועדון")
                    .setMessage("האם אתה בטוח שאתה רוצה למחוק את המועדון מרשימת המועדונים שלך?")
                    .setPositiveButton("כן", (dialog, which) -> {
                        // פתח את חלונית הפרטים
                        AppManagerFirebase.removeClubFromUser(clubMembership.getClubId());
                        user.removeClubMembership(clubMembership.getClubId());
                        clubMembershipsList.remove(clubMembership.getClubId());
                    })
                    .setNegativeButton("לא", (dialog, which) -> {
                        // סגור את החלונית, לא תבצע כל פעולה
                    })
                    .show();
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView TV_club_name, TV_club_card_number, TV_club_expiry_date, TV_club_accepted_stores;
        ImageView clubLogo;
        ShapeableImageView removeClub_IV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clubLogo = itemView.findViewById(R.id.IV_club_logo);
            TV_club_name = itemView.findViewById(R.id.TV_club_name);
            TV_club_card_number = itemView.findViewById(R.id.TV_club_card_number);
            TV_club_expiry_date = itemView.findViewById(R.id.TV_club_expiry_date);
            TV_club_accepted_stores = itemView.findViewById(R.id.TV_club_accepted_stores);
            removeClub_IV = itemView.findViewById(R.id.remove_club_IMG);
        }
    }
}
