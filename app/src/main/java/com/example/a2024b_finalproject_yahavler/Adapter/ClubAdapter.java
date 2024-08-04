package com.example.a2024b_finalproject_yahavler.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {
    private Context context;
    private List<Club> clubList;
    private OnClubClickListener onClubClickListener;
    private String selectedClubId = "";

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
    public interface OnClubClickListener {
        void onClubClick(Club club);
        void onSaveClubMembership(String clubId, String cardNumber, String expiryDate);
    }
    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = clubList.get(position);
        holder.clubName.setText(club.getName());
        ImageLoader.getInstance().load(club.getLogoResId(), holder.clubLogo);
        holder.CV_club_details.setVisibility(View.GONE);
        selectedClubId = club.getClubId(); // עדכון ה-clubId
        holder.btnAddClub.setOnClickListener(v -> {
            if(holder.CV_club_details.getVisibility() == View.VISIBLE){
                holder.CV_club_details.setVisibility(View.GONE);
            }else{
                holder.CV_club_details.setVisibility(View.VISIBLE);
            }
        });
        holder.saveButton.setOnClickListener(v -> {
            if (onClubClickListener != null) {
                onClubClickListener.onSaveClubMembership(
                        selectedClubId,
                        holder.tvClubCardNumber.getText().toString(),
                        holder.tvClubExpiryDate.getText().toString()
                );
            }
        });
        holder.tvClubExpiryDate.setOnClickListener(v -> showDatePickerDialog(holder.tvClubExpiryDate));
    }
    private void showDatePickerDialog(TextView tvClubExpiryDate) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,  // שימוש ב-context במקום this
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the selected date (you can customize this format)
                    String formattedDate = String.format(Locale.getDefault(), "%02d/%d", selectedMonth + 1, selectedYear);
                    tvClubExpiryDate.setText(formattedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {
        TextView clubName;
        ImageView clubLogo;
        ImageButton btnAddClub;
        CardView CV_club_details;
        Button saveButton;
        TextView tvClubCardNumber;
        TextView tvClubExpiryDate;
        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            clubName = itemView.findViewById(R.id.club_name);
            clubLogo = itemView.findViewById(R.id.IV_club_logo);
            btnAddClub = itemView.findViewById(R.id.btn_add_club);
            CV_club_details = itemView.findViewById(R.id.CV_club_details);
            saveButton = itemView.findViewById(R.id.save_button);
            tvClubCardNumber = itemView.findViewById(R.id.TV_club_card_number);
            tvClubExpiryDate = itemView.findViewById(R.id.TV_club_expiry_date);
            CV_club_details.setVisibility(View.GONE);
        }
    }
}
