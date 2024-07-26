package com.example.a2024b_finalproject_yahavler.ActivityView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2024b_finalproject_yahavler.NevigationActivity;
import com.example.a2024b_finalproject_yahavler.ObjectCallback;
import com.example.a2024b_finalproject_yahavler.R;

import com.google.gson.Gson;

public class stores_of_club_home_view extends AppCompatActivity implements ObjectCallback {

    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores_of_club_home_view);

        findView();
        NevigationActivity.findNevigationButtens(this);

    }
    private void findStore(String store) {

    }
    private void initViewsStores(){
    }

    @Override
    public void onObjectClick(Object object) {

    }




    private void findView() {

    }
}