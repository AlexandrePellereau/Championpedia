package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.champion.ContextProvider;
import com.alexpell.championpedia.champion.Initialise;
import com.alexpell.championpedia.databinding.ActivityMainBinding;
import com.alexpell.championpedia.landing_page.LandingPageActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        ContextProvider.initialize(getApplicationContext());
        if (!sharedPreferences.getBoolean("db",false)){
            try {
                Initialise.initialiseDB(getApplicationContext());
                editor = sharedPreferences.edit();
                editor.putBoolean("db",true);
                editor.apply();
                Toast.makeText(this, "db ++", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (sharedPreferences.getBoolean("loggedIn", false))
            startActivity(LandingPageActivity.LandingPageIntentFactory(getApplicationContext()));
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.LoginActivityIntentFactory(getApplicationContext(),false);
                startActivity(intent);
            }
        });

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.LoginActivityIntentFactory(getApplicationContext(),true);
                startActivity(intent);
            }
        });



    }

    public static Intent MainActivityIntentFactory(Context context){
        return new Intent(context,MainActivity.class);
    }
}