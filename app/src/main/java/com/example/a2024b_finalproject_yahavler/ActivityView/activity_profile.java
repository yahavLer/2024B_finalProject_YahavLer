package com.example.a2024b_finalproject_yahavler.ActivityView;

import com.example.a2024b_finalproject_yahavler.Model.User;
import com.example.a2024b_finalproject_yahavler.R;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2024b_finalproject_yahavler.Managers.NevigationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class activity_profile extends AppCompatActivity {
    private TextView EDT_Hello;
    private TextView EDT_username;
    private TextView EDT_email;
    private TextView EDT_phone;
    private ImageView profileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        findView();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // משיכת נתוני המשתמש
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            loadUserProfile(userId);
        }

        // הפעלת ניווט
        NevigationActivity.findNevigationButtens(this);
    }

    private void findView() {
        EDT_Hello = findViewById(R.id.Hello);
        EDT_username = findViewById(R.id.profile_EDT_username);
        EDT_email = findViewById(R.id.profile_EDT_email);
        EDT_phone = findViewById(R.id.profile_phone);
        profileImage = findViewById(R.id.profile_image);
    }

    private void loadUserProfile(String userId) {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    EDT_Hello.setText("Hello " + user.getUsername());
                    EDT_username.setText(user.getUsername());
                    EDT_email.setText(user.getEmail());
                    EDT_phone.setText(user.getPhone());

                    // כאן נוכל לטעון את תמונת הפרופיל
                    // לדוגמה אם התמונה מאוחסנת כ-URL:
                    // Glide.with(activity_profile.this).load(user.getProfileImageUrl()).into(profileImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // טיפול בשגיאות
            }
        });
    }

}
