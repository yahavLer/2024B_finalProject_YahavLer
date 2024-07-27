package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.example.a2024b_finalproject_yahavler.R;

public class activity_login extends AppCompatActivity {
    private Gson gson = new Gson();
    private AppCompatEditText Login_EDT_Name;
    private AppCompatEditText Login_EDT_Phone;
    private MaterialButton Login_BTN_Login;
    private String name;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        findView();

        Login_BTN_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login
                name = Login_EDT_Name.getText().toString();
                phone = Login_EDT_Phone.getText().toString();
                Intent intent = new Intent(activity_login.this, stores_of_club_home_view.class);
                startActivity(intent);
            }
        });
    }


    private void findView() {
        Login_EDT_Name =findViewById(R.id.Login_EDT_Name);
        Login_EDT_Phone =findViewById(R.id.Login_EDT_Phone);
        Login_BTN_Login =findViewById(R.id.Login_BTN_Login);
    }
}
