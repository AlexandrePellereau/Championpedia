package com.alexpell.championpedia.champion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.comment.CommentActivity;
import com.alexpell.championpedia.databinding.ActivityChampionBinding;
import com.alexpell.championpedia.landing_page.LandingPageActivity;

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

        champion = allDAO.getChampionByName(championName);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));
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

        binding.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CommentActivity.class));
            }
        });

        setTexts(champion);
    }

    public static String ParseString(String s) {
        return s.toLowerCase().replace("'","");
    }

    private void setTexts(Champion champion) {
        int resourceId = getResources().getIdentifier(ParseString(champion.getName()), "drawable", getPackageName());

        binding.championName.setText(champion.getName());

        binding.championImage.setImageResource(resourceId);

        binding.championLore.setText(champion.getLore());

        binding.banrate.setText(String.valueOf(champion.getBanrate()));

        binding.pickrate.setText(String.valueOf(champion.getPickrate()));

        binding.winrate.setText(String.valueOf(champion.getWinrate()));

    }
}