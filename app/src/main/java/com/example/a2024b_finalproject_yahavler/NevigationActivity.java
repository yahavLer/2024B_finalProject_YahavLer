package com.example.a2024b_finalproject_yahavler;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.a2024b_finalproject_yahavler.ActivityView.activity_profile;

import com.example.a2024b_finalproject_yahavler.ActivityView.stores_of_club_home_view;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class NevigationActivity {

    public static void findNevigationButtens(Activity actvity) {
        Gson gson = new Gson();
        MaterialButton navigation_home = actvity.findViewById(R.id.navigation_home);
        MaterialButton navigation_profile = actvity.findViewById(R.id.navigation_profile);
        MaterialButton navigation_clubs = actvity.findViewById(R.id.navigation_clubs);
        navigation_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(actvity, stores_of_club_home_view.class);
                actvity.startActivity(intentHome);
            }
        });

        navigation_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(actvity, activity_profile.class);
                actvity.startActivity(intentProfile);
            }
        });

    }
}



