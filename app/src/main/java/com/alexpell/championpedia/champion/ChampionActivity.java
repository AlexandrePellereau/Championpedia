package com.alexpell.championpedia.champion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.LandingPage;
import com.alexpell.championpedia.databinding.ActivityChampionBinding;

public class ChampionActivity extends AppCompatActivity {

    ActivityChampionBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AllDAO allDAO;
    AppDataBase database;
    Champion champion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChampionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the root view of the inflated layout
        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();


        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);

        String championName = sharedPreferences.getString("champion","velkoz");

        /* Crash : champion = championDAO.getChampionByName("Aatrox");*/
        Log.d("ChampionActivity", "onCreate: "+championName);
        champion = allDAO.getChampionByName(championName);
        Log.d("ChampionActivity", "onCreate: "+champion);
        Log.d("ChampionActivity", "onCreate: "+champion.getName());
        Toast.makeText(this, champion.getName(), Toast.LENGTH_SHORT).show();


        int resourceId = getResources().getIdentifier(championName, "drawable", getPackageName());
       //  Crash : binding.championImage.setImageResource(resourceId);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LandingPage.class));

            }
        });

        binding.fullLoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FullLoreActivity.class));
            }
        });

        binding.addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddReview.class));
            }
        });
    }
}