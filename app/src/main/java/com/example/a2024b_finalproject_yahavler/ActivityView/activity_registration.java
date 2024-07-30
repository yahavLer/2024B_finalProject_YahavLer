package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Managers.ImageLoader;
import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class activity_registration extends AppCompatActivity {
    private TextInputLayout signup_EDT_username;
    private TextInputLayout signup_EDT_phone;
    private MaterialButton signup_BTN_Register;
    private User newUser = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
        findView();



        signup_BTN_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                createNewUser(signup_EDT_username.getText().toString(),signup_EDT_phone.getText().toString());

                Intent intent = new Intent(activity_registration.this, stores_of_club_home_view.class);
                startActivity(intent);
            }
        });
    }
    private void createNewUser(String name, String phoneNumber){
        newUser.setUserId().setName(name).setPhoneNumber(phoneNumber);
        AppManagerFirebase.addUser(newUser);
    }


    private void findView() {
        signup_EDT_username =findViewById(R.id.registration_name);
        signup_EDT_phone = findViewById(R.id.registration_phone);
        signup_BTN_Register = findViewById(R.id.signup_BTN_Register);
    }
}