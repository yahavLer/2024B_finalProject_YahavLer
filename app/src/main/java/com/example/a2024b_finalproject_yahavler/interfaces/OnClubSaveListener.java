package com.example.a2024b_finalproject_yahavler.interfaces;

import android.widget.LinearLayout;

import com.example.a2024b_finalproject_yahavler.Model.ClubMembership;

public interface OnClubSaveListener {
    void onSaveClubMembership(ClubMembership membership, int position, LinearLayout inputDetailsLayout);
}