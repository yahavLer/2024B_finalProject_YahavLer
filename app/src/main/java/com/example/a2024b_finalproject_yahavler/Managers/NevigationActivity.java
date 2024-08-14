package com.example.a2024b_finalproject_yahavler.Managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.a2024b_finalproject_yahavler.ActivityView.activity_manage_club;
import com.example.a2024b_finalproject_yahavler.ActivityView.activity_profile;

import com.example.a2024b_finalproject_yahavler.ActivityView.stores_of_club_home_view;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class NevigationActivity {

    public static void findNevigationButtens(Activity activity) {
        MaterialButton navigation_home = activity.findViewById(R.id.navigation_home);
        MaterialButton navigation_profile = activity.findViewById(R.id.navigation_profile);
        MaterialButton navigation_clubs = activity.findViewById(R.id.navigation_clubs);
        MaterialButton navigation_store_locations = activity.findViewById(R.id.navigation_store_locations);

        SharedPreferences sharedPreferences = activity.getSharedPreferences("NavigationPrefs", Context.MODE_PRIVATE);
        int selectedButtonId = sharedPreferences.getInt("selectedButtonId", R.id.navigation_home);

        // Reset all buttons to their default color
        setButtonColor(navigation_home, R.color.inactiveButtonColor);
        setButtonColor(navigation_profile, R.color.inactiveButtonColor);
        setButtonColor(navigation_clubs, R.color.inactiveButtonColor);
        setButtonColor(navigation_store_locations, R.color.inactiveButtonColor);

        // Set the selected button color
        setButtonColor(activity.findViewById(selectedButtonId), R.color.activeButtonColor);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset all buttons to their default color
                setButtonColor(navigation_home, R.color.inactiveButtonColor);
                setButtonColor(navigation_profile, R.color.inactiveButtonColor);
                setButtonColor(navigation_clubs, R.color.inactiveButtonColor);
                setButtonColor(navigation_store_locations, R.color.inactiveButtonColor);

                // Set the selected button color
                setButtonColor(v, R.color.activeButtonColor);

                // Save the selected button ID
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selectedButtonId", v.getId());
                editor.apply();

                Intent intent;
                if (v.getId() == R.id.navigation_home) {
                    intent = new Intent(activity, stores_of_club_home_view.class);
                } else if (v.getId() == R.id.navigation_profile) {
                    intent = new Intent(activity, activity_profile.class);
                } else if (v.getId() == R.id.navigation_clubs) {
                    intent = new Intent(activity, activity_manage_club.class);
                } else {
                    return;
                }
                // Use Intent.FLAG_ACTIVITY_CLEAR_TOP to clear the back stack and navigate to the selected activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        };

        navigation_home.setOnClickListener(listener);
        navigation_profile.setOnClickListener(listener);
        navigation_clubs.setOnClickListener(listener);
        navigation_store_locations.setOnClickListener(listener);
    }

    public static void setButtonColor(View button, int color) {
        button.setBackgroundColor(button.getContext().getResources().getColor(color, null));
    }
}



