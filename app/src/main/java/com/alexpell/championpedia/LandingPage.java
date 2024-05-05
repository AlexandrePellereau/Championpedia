package com.alexpell.championpedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alexpell.championpedia.comment.CommentActivity;
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

        //TODO : remove (for testing purposes)
        binding.adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CommentActivity.class));
            }
        });
    }

}
