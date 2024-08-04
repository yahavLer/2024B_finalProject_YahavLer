package com.example.a2024b_finalproject_yahavler.ActivityView;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.RenderEffect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.a2024b_finalproject_yahavler.Managers.AppManagerFirebase;
import com.example.a2024b_finalproject_yahavler.Model.User;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.example.a2024b_finalproject_yahavler.R;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class activity_login extends AppCompatActivity {
    private TextInputLayout login_EDT_email;
    private TextInputLayout login_EDT_password;
    private MaterialButton login_BTN_Login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        findView();

        mAuth = FirebaseAuth.getInstance();

        login_BTN_Login.setOnClickListener(v -> {
            String email = login_EDT_email.getEditText().getText().toString().trim();
            String password = login_EDT_password.getEditText().getText().toString().trim();

            if (validateInputs(email, password)) {
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(activity_login.this, stores_of_club_home_view.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // טיפול בשגיאת התחברות
                        Toast.makeText(activity_login.this, "Login failed. Please check your credentials", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateInputs(String email, String password) {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_EDT_email.setError("Valid email is required");
            login_EDT_email.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            login_EDT_password.setError("Password is required");
            login_EDT_password.requestFocus();
            return false;
        }
        return true;
    }

    private void findView() {
        login_EDT_email = findViewById(R.id.login_EDT_email);
        login_EDT_password = findViewById(R.id.login_EDT_password);
        login_BTN_Login = findViewById(R.id.Login_BTN_Login);
    }
}

