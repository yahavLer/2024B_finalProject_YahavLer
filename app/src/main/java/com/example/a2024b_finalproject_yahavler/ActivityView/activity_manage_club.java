package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2024b_finalproject_yahavler.Callback.ManageClubCallback;
import com.example.a2024b_finalproject_yahavler.Callback.ObjectCallback;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.a2024b_finalproject_yahavler.Adapter.ClubAdapter;
import com.example.a2024b_finalproject_yahavler.DataManagers.ClubManager;
import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class activity_manage_club extends AppCompatActivity implements ObjectCallback {
    private RecyclerView main_LST_club;
    private ArrayList<Club> clubs = new ArrayList<>();
    private ClubAdapter clubAdapter;
    private FirebaseUser currentUser;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_clubs);
        findView();
        currentUser=AppManagerFirebase.getCurrentUser();
        initAllClubs();
        NevigationActivity.findNevigationButtens(this);
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
    }

    private void findView() {
        main_LST_club = findViewById(R.id.clubs_recycler_view);
    }

    private void initAllClubs() {
        AppManagerFirebase.fetchAllClubs(new AppManagerFirebase.CallBack<ArrayList<Club>>() {
            @Override
            public void res(ArrayList<Club> allClubs) {
                if (allClubs != null) {
                    clubs.addAll(allClubs);
                    clubAdapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_club.setLayoutManager(linearLayoutManager);
        clubAdapter = new ClubAdapter(clubs, this, currentUser.getUid());
        main_LST_club.setAdapter(clubAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause called");
        // Save any necessary state here
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy called");
        // Clean up any resources here
    }

    @Override
    public void onObjectClick(Object object) {

    }
}