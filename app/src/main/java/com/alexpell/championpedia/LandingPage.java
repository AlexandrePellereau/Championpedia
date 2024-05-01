package com.alexpell.championpedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alexpell.championpedia.databinding.ActivityMainBinding;
import com.alexpell.championpedia.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {

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

        setChampions();

    }

    private void setChampions(){
        binding.imageAatrox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","aatrox");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageAhri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","ahri");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageAkali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","akali");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageKarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","karma");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });

        binding.imageVelKoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putString("champion","velkoz");
                editor.apply();
                startActivity(new Intent(getApplicationContext(),ChampionActivity.class));
            }
        });
    }

}
