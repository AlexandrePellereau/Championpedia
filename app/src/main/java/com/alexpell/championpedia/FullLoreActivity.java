package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.ChampionDAO;
import com.alexpell.championpedia.databinding.ActivityChampionBinding;
import com.alexpell.championpedia.databinding.ActivityFullLoreBinding;

public class FullLoreActivity extends AppCompatActivity {

    ActivityFullLoreBinding binding;
    SharedPreferences sharedPreferences;
    Champion champion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullLoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the root view of the inflated layout

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });
/*
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        String championName = sharedPreferences.getString("champion","Aatrox");
        ChampionDAO championDAO = AppDataBase.getInstance(getApplicationContext()).championsDAO();
        int resourceId = getResources().getIdentifier(championName, "drawable", getPackageName());
        champion = championDAO.getChampionByName(championName);
        binding.championImage.setImageResource(resourceId);
        binding.championName.setText(championName);
        binding.championLore.setText(champion.getLore());
*/
    }
}