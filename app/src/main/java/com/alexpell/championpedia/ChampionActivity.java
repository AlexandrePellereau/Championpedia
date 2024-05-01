package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.ChampionDAO;
import com.alexpell.championpedia.databinding.ActivityChampionBinding;

public class ChampionActivity extends AppCompatActivity {

    ActivityChampionBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ChampionDAO championDAO;
    AppDataBase database;

    Champion champion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChampionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the root view of the inflated layout
        database = AppDataBase.getInstance(getApplicationContext()); // Obtain your database instance
        championDAO = database.championsDAO(); // Obtain ChampionDAO instance


        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);

        String championName = sharedPreferences.getString("champion","velkoz");

       // champion = championDAO.getChampionByName(championName);

        int resourceId = getResources().getIdentifier(championName, "drawable", getPackageName());
        binding.championImage.setImageResource(resourceId);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LandingPage.class));

            }
        });

      //  binding.championWr.setText(champion.getLore());
    }

}