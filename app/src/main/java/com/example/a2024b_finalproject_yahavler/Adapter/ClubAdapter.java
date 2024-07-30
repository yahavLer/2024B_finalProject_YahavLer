package com.example.a2024b_finalproject_yahavler.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.R;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {
    private Context context;
    private List<Club> clubList;
    private OnClubClickListener onClubClickListener;

    public ClubAdapter(List<Club> clubList, Context context, OnClubClickListener onClubClickListener) {
        this.clubList = clubList;
        this.context = context;
        this.onClubClickListener = onClubClickListener;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_item_object, parent, false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = clubList.get(position);
        holder.clubName.setText(club.getName());
        ImageLoader.getInstance().load(club.getLogoResId(), holder.clubLogo);
        holder.CV_club_details.setVisibility(View.GONE);
        holder.btnAddClub.setOnClickListener(v -> {
            if(holder.CV_club_details.getVisibility() == View.VISIBLE){
                holder.CV_club_details.setVisibility(View.GONE);
            }else{
                holder.CV_club_details.setVisibility(View.VISIBLE);
            }
            // Call the method in activity to show club details
        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public interface OnClubClickListener {
        void onClubClick(Club club);
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {
        TextView clubName;
        ImageView clubLogo;
        ImageButton btnAddClub;
        CardView CV_club_details;
        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            clubName = itemView.findViewById(R.id.club_name);
            clubLogo = itemView.findViewById(R.id.IV_club_logo);
            btnAddClub = itemView.findViewById(R.id.btn_add_club);
            CV_club_details = itemView.findViewById(R.id.CV_club_details);
            CV_club_details.setVisibility(View.GONE);
        }
    }
}
