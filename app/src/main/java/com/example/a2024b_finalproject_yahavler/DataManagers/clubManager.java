package com.example.a2024b_finalproject_yahavler.DataManagers;

import com.example.a2024b_finalproject_yahavler.Model.Club;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class clubManager {
    public static ArrayList<Club> getMovies() {
        ArrayList<Club> clubs = new ArrayList<>();

        clubs.add(new Club()
                .setName("Wonka")
                .setLogoResId("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uxBHXaoOvAwy4NpPpP7nNx2rXYQ.jpg")
        );


        clubs.add(new Club()
                .setName("Deadpool & Wolverine")
                .setLogoResId("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uxBHXaoOvAwy4NpPpP7nNx2rXYQ.jpg")
        );

        return clubs;
    }
}
