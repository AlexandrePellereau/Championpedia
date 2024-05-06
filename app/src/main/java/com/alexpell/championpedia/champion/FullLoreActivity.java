package com.alexpell.championpedia.champion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.MainActivity;
import com.alexpell.championpedia.databinding.ActivityFullLoreBinding;

public class FullLoreActivity extends AppCompatActivity {

    ActivityFullLoreBinding binding;
    SharedPreferences sharedPreferences;
    Champion champion;

    AllDAO allDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullLoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the root view of the inflated layout
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });
        String championName = sharedPreferences.getString("champion","velkoz");
        int resourceId = getResources().getIdentifier(MainActivity.parseString(championName), "drawable", getPackageName());
        champion = allDAO.getChampionByName(championName);
        binding.championImage.setImageResource(resourceId);
        binding.championName.setText(championName);
        binding.championLore.setText(champion.getFull_lore());
    }
}