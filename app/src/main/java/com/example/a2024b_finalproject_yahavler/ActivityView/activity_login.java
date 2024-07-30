package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.content.Intent;
import android.graphics.RenderEffect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.example.a2024b_finalproject_yahavler.R;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class activity_login extends AppCompatActivity {
    private Gson gson = new Gson();
    private TextInputLayout Login_EDT_Name;
    private TextInputLayout Login_EDT_Phone;
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
                login();
            }
        });
    }
    private void login(){
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.PhoneBuilder().build()))
                .build();
        signInLauncher.launch(signInIntent);
    }
    private void validateLogin() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        firebaseUser.getUid();
        if (firebaseUser!=null){
//            name = Login_EDT_Name.getText().toString();
//            phone = Login_EDT_Phone.getText().toString();
            Intent intent = new Intent(activity_login.this, stores_of_club_home_view.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Error: Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
    private ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            (result) -> {
                validateLogin();
            });

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
    }

    private void findView() {
        Login_EDT_Name =findViewById(R.id.Login_EDT_Name);
        Login_EDT_Phone =findViewById(R.id.Login_EDT_Phone);
        Login_BTN_Login =findViewById(R.id.Login_BTN_Login);
    }
}
