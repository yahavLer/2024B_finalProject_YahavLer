package com.example.a2024b_finalproject_yahavler.ActivityView;

import com.example.a2024b_finalproject_yahavler.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.a2024b_finalproject_yahavler.NevigationActivity;
import com.google.gson.Gson;

public class activity_profile extends AppCompatActivity {
    TextView EDT_Hello;
    TextView EDT_username;
    TextView EDT_email;
    TextView EDT_userId;
    TextView EDT_role;
    ImageView profileImage;
    Gson gson= new Gson();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        findView();
        NevigationActivity.findNevigationButtens(this);

    }

    private void findView() {
        EDT_Hello = findViewById(R.id.Hello);
        EDT_username = findViewById(R.id.profile_EDT_username);
        profileImage = findViewById(R.id.profile_image);
    }
}
