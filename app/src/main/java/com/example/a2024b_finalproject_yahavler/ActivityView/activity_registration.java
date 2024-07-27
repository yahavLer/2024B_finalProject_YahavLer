package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.a2024b_finalproject_yahavler.Managers.AppManager;
import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_registration extends AppCompatActivity {
    private AppCompatEditText signup_EDT_username;
    private AppCompatEditText signup_EDT_phone;
    private Spinner signup_SPN_club_membership;
    private MaterialButton signup_BTN_Register;
    private Gson gson = new Gson();
    private AppManager appManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
        findView();
        appManager = new AppManager();
        signup_BTN_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser(signup_EDT_username.getText().toString(),signup_EDT_phone.getText().toString());

                Intent intent = new Intent(activity_registration.this, stores_of_club_home_view.class);
                startActivity(intent);
            }
        });
    }
    private void createNewUser(String name, String phoneNumber){
        User newUser = new User(name,phoneNumber);
        appManager.addUser(newUser);
    }
    private void findView() {
        signup_EDT_username =findViewById(R.id.registration_name);
        signup_EDT_phone = findViewById(R.id.registration_phone);
        signup_SPN_club_membership = findViewById(R.id.signup_SPN_club_membership);
        signup_BTN_Register = findViewById(R.id.signup_BTN_Register);
        String[] clubArray = getResources().getStringArray(R.array.clubs_membership);
        ArrayAdapter<String> clubAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,clubArray);
        signup_SPN_club_membership.setAdapter(clubAdapter);
    }
}