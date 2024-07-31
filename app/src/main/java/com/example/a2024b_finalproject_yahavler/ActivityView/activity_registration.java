package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class activity_registration extends AppCompatActivity {
    private TextInputLayout signup_EDT_username;
    private TextInputLayout signup_EDT_phone;
    private TextInputLayout signup_EDT_email;
    private TextInputLayout signup_EDT_password;
    private MaterialButton signup_BTN_Register;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
        findView();

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        signup_BTN_Register.setOnClickListener(v -> {
            String username = signup_EDT_username.getEditText().getText().toString().trim();
            String email = signup_EDT_email.getEditText().getText().toString().trim();
            String phone = signup_EDT_phone.getEditText().getText().toString().trim();
            String password = signup_EDT_password.getEditText().getText().toString().trim();

            if (validateInputs(username, email, password, phone)) {
                registerUser(username, phone, email, password);
            }
        });
    }

    private void registerUser(String username, String phone, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        User newUser = new User(userId, username, email, phone);

                        databaseReference.child(userId).setValue(newUser)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Intent intent = new Intent(activity_registration.this, activity_login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // טיפול בשגיאה בהוספת נתונים לבסיס הנתונים
                                    }
                                });
                    } else {
                        // טיפול בשגיאה ביצירת משתמש
                    }
                });
    }

    private boolean validateInputs(String username, String email, String password, String phone) {
        if (TextUtils.isEmpty(username)) {
            signup_EDT_username.setError("Username is required");
            signup_EDT_username.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signup_EDT_email.setError("Valid email is required");
            signup_EDT_email.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            signup_EDT_password.setError("Password must be at least 6 characters");
            signup_EDT_password.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(phone) || !Patterns.PHONE.matcher(phone).matches()) {
            signup_EDT_phone.setError("Valid phone number is required");
            signup_EDT_phone.requestFocus();
            return false;
        }

        return true;
    }

    private void findView() {
        signup_EDT_username = findViewById(R.id.registration_name);
        signup_EDT_phone = findViewById(R.id.registration_phone);
        signup_EDT_email = findViewById(R.id.registration_EDT_email);
        signup_EDT_password = findViewById(R.id.registration_EDT_password);
        signup_BTN_Register = findViewById(R.id.signup_BTN_Register);
    }
}
