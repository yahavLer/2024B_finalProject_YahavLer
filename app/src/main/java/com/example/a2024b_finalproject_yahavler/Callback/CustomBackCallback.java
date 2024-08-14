package com.example.a2024b_finalproject_yahavler.Callback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.activity.OnBackPressedCallback;

import com.example.a2024b_finalproject_yahavler.ActivityView.stores_of_club_home_view;
import com.example.a2024b_finalproject_yahavler.R;

public class CustomBackCallback extends OnBackPressedCallback {

    private final Activity activity;

    public CustomBackCallback(Activity activity) {
        super(true);
        this.activity = activity;
    }

    @Override
    public void handleOnBackPressed() {
        // שמירת ה-ID של כפתור מסך הבית בזיכרון המשותף
        SharedPreferences sharedPreferences = activity.getSharedPreferences("NavigationPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("selectedButtonId", R.id.navigation_home);
        editor.apply();

        if (activity.isTaskRoot()) {
            // אם זה activity השורש, לסגור את כל הפעילויות ולצאת מהאפליקציה
            activity.finishAffinity(); // Closes all activities and exits the app
        } else {
            // אחרת, לנווט למסך הבית
            Intent intent = new Intent(activity, stores_of_club_home_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the back stack
            activity.startActivity(intent);
            activity.finish(); // לסגור את ה-Activity הנוכחי כדי להבטיח שלא יישאר בערימת הניווט
        }
    }
}
