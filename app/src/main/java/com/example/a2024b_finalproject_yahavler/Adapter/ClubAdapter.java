package com.example.a2024b_finalproject_yahavler.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Callback.ManageClubCallback;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {
    private Context context;
    private List<Club> clubList;
    private ManageClubCallback manageClubCallback;
    private String selectedClubId = "";
    private String curUserIdFire;
    private User user = new User();

    public ClubAdapter(List<Club> clubList, Context context, String curUserIdFire) {
        this.clubList = clubList;
        this.context = context;
        this.curUserIdFire =curUserIdFire;
    }

    public void setClubClickListener(ManageClubCallback manageClubCallback)  {
        this.manageClubCallback=manageClubCallback;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_item_object, parent, false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = getItem(position);
        setupClubDetails(holder, club);
        setupClickListeners(holder, club, position);
    }

    private void setupClubDetails(@NonNull ClubViewHolder holder, Club club) {
        ImageLoader.init(context);
        holder.clubName.setText(club.getName());
        ImageLoader.getInstance().load(club.getLogoResId(), holder.clubLogo);
        holder.CV_club_details.setVisibility(View.GONE);
    }

    private void setupClickListeners(@NonNull ClubViewHolder holder, Club club, int position) {
        holder.btnAddClub.setOnClickListener(v -> {
            if(holder.CV_club_details.getVisibility() == View.VISIBLE){
                holder.CV_club_details.setVisibility(View.GONE);
            }else{
                holder.CV_club_details.setVisibility(View.VISIBLE);
            }
        });
        holder.ET_ClubExpiryDate.setOnClickListener(v -> showDatePickerDialog(holder.ET_ClubExpiryDate));
        holder.saveButton.setOnClickListener(v -> {
            selectedClubId = club.getClubId();// עדכון ה-clubId
            Log.e("selectedClubId",selectedClubId);
            saveClubMembership( selectedClubId,
                holder.ET_ClubCardNumber.getText().toString(),
                holder.ET_ClubExpiryDate.getText().toString());
        });
    }

    public void saveClubMembership(String clubId, String cardNumber, String expiryDate) {
        Log.e("saveClubMembership",selectedClubId);
        Date parsedExpiryDate = null;
        try {
            parsedExpiryDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).parse(expiryDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        // יצירת ה-ClubMembership החדש
        ClubMembership newMembership = new ClubMembership(curUserIdFire, clubId, cardNumber, parsedExpiryDate);
        user.addClubMembership(newMembership);
        // עדכון רשימת החברות של המשתמש ב-Firebase
        AppManagerFirebase.addClubMembership(newMembership, curUserIdFire, success -> {
            if (success) {
                Toast.makeText(context, "Club added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to update club membership", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog(EditText ET_ClubExpiryDate) {
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
                    ET_ClubExpiryDate.setText(formattedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }
    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public Club getItem(int position) {
        return clubList.get(position);
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {
        TextView clubName;
        ImageView clubLogo;
        ImageButton btnAddClub;
        CardView CV_club_details;
        Button saveButton;
        EditText ET_ClubCardNumber, ET_ClubExpiryDate;
        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            clubName = itemView.findViewById(R.id.club_name);
            clubLogo = itemView.findViewById(R.id.IV_club_logo);
            btnAddClub = itemView.findViewById(R.id.btn_add_club);
            CV_club_details = itemView.findViewById(R.id.CV_club_details);
            saveButton = itemView.findViewById(R.id.save_button);
            ET_ClubCardNumber = itemView.findViewById(R.id.ET_club_card_number);
            ET_ClubExpiryDate = itemView.findViewById(R.id.ET_club_expiry_date);
            CV_club_details.setVisibility(View.GONE);
        }
    }
}
