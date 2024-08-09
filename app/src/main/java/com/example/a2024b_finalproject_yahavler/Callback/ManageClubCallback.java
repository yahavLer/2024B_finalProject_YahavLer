package com.example.a2024b_finalproject_yahavler.Callback;

import com.example.a2024b_finalproject_yahavler.Model.Club;

import java.util.List;

public interface ManageClubCallback {
    void onClubClick(Club club);
    void onSaveClubMembership(String clubId, String cardNumber, String expiryDate);
}
