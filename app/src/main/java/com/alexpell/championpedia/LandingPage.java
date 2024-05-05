package com.alexpell.championpedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.admin.AdminActivity;
import com.alexpell.championpedia.champion.ChampionActivity;
import com.alexpell.championpedia.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {

    AllDAO allDAO;
    SharedPreferences sharedPreferences;
    ActivityLandingPageBinding binding;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        if (sharedPreferences.getBoolean("isAdmin",false)){
            binding.adminButton.setVisibility(View.VISIBLE);
        }
        else{
            binding.adminButton.setVisibility(View.INVISIBLE);
        }
        View view = binding.getRoot();
        setContentView(view);
        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();
        binding.loginText.setText(String.format("%s%s", getString(R.string.logged_in_as), allDAO.getUser(sharedPreferences.getInt("userId", 0)).getUsername()));

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putBoolean("loggedIn",false);
                editor.putBoolean("isAdmin",false);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        binding.adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        });
        setChampions();
    }

    private void setChampions(){
        binding.imageAatrox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","Aatrox");
                editor.apply();
                startActivity(new Intent(getApplicationContext(), ChampionActivity.class));
            }
        });

        binding.imageAhri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","Ahri");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageAkali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","Akali");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageKarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","Karma");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageVelKoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","Vel'koz");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });
    }

}
