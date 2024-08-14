package com.example.a2024b_finalproject_yahavler.Callback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2024b_finalproject_yahavler.ActivityView.stores_of_club_home_view;

public class CustomBackCallback extends OnBackPressedCallback {

    private final Activity activity;

    public CustomBackCallback(Activity activity) {
        super(true);
        this.activity = activity;
    }

    @Override
    public void handleOnBackPressed() {
        if (activity.isTaskRoot()) {
            // If it's the root activity, exit the app
            activity.finishAffinity(); // Closes all activities and exits the app
        } else {
            // Otherwise, navigate to the main screen
            Intent intent = new Intent(activity, stores_of_club_home_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the back stack
            activity.startActivity(intent);
        }
    }
}
