package com.example.a2024b_finalproject_yahavler.DataManagers;

import com.example.a2024b_finalproject_yahavler.Model.Club;
import com.example.a2024b_finalproject_yahavler.Model.Store;

import java.util.ArrayList;
import java.util.Arrays;

public class ClubManager {
    public static ArrayList<Club>getClub(){
        ArrayList<Club> clubs = new ArrayList<>();
        clubs=createClubs();
        for (Club club : clubs) {
            club.setAcceptedStores(getStoresForClub(club.getName()));
           // getStoresForClub(club.getClubId());
        }
        return clubs;
    }
    public static ArrayList<Club> createClubs() {
        ArrayList<Club> clubs = new ArrayList<>();

        clubs.add(new Club()
                .setClubId()
                .setName("בהצדעה")
                .setLogoResId("https://www.behatsdaa.org.il/static/media/beatsdaha-new-logo.066a9ce6.png")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("חבר")
                .setLogoResId("https://www.hvr.co.il/acmplt.asmx/logo?t=963322150470")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("אשמורת")
                .setLogoResId("https://digital.isracard.co.il/globalassets/cardsimages/131_800_-1/a_ashmoret_gold.png")
        );
        clubs.add(new Club()
                .setClubId()
                .setName("ביחד בשבילך")
                .setLogoResId("data:image/png;base64,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")
        );

        return clubs;
    }
    public static ArrayList<String> getStoresForClub(String clubName) {
        ArrayList<String> storesAcceptingClub = new ArrayList<>();
        ArrayList<Store> stores = StoreManager.getStores();

        for (Store store : stores) {
            if (store.getAcceptedClubs().contains(clubName)) {
                storesAcceptingClub.add(store.getName());
            }
        }

        return storesAcceptingClub;
    }
}
