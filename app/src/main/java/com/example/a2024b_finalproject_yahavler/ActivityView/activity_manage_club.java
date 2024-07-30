package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Adapter.ClubAdapter;
import com.example.a2024b_finalproject_yahavler.DataManagers.ClubManager;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class activity_manage_club extends AppCompatActivity implements ClubAdapter.OnClubClickListener {
    private RecyclerView main_LST_club;
    private ArrayList<Club> clubs = new ArrayList<>();
    private CardView clubDetailsCard;
    private EditText tvClubCardNumber, tvClubExpiryDate;
    private Button saveButton;
    private String selectedClubId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_clubs);
        findView();
        initAllStores();
        NevigationActivity.findNevigationButtens(this);
        // saveButton.setOnClickListener(v -> saveClubMembership());
    }

    @Override
    public void onClubClick(Club club) {
        selectedClubId = club.getClubId();
        clubDetailsCard.setVisibility(View.VISIBLE);
    }

    private void findView() {
        main_LST_club = findViewById(R.id.clubs_recycler_view);
        clubDetailsCard = findViewById(R.id.CV_club_details);
        tvClubCardNumber = findViewById(R.id.TV_club_card_number);
        tvClubExpiryDate = findViewById(R.id.TV_club_expiry_date);
        saveButton = findViewById(R.id.save_button);
    }

    private void initAllStores(){
        clubs = ClubManager.getClub();
        ClubAdapter clubAdapter = new ClubAdapter(clubs, this, this);
        AppManagerFirebase.addAllClubs(clubs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_club.setLayoutManager(linearLayoutManager);
        main_LST_club.setAdapter(clubAdapter);
    }

    private void saveClubMembership() {
        String cardNumber = tvClubCardNumber.getText().toString();
        String expiryDateStr = tvClubExpiryDate.getText().toString();
        Date expiryDate = parseDate(expiryDateStr);

        if (selectedClubId.isEmpty() || cardNumber.isEmpty() || expiryDate == null) {
            // Handle error: show a message to the user
            return;
        }

        ClubMembership clubMembership = new ClubMembership(
                "currentUserId", // Replace with actual user ID
                selectedClubId,
                cardNumber,
                expiryDate
        );

        // Save clubMembership object (implement the save logic)
        // For example: ClubManager.saveClubMembership(clubMembership);
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
}
