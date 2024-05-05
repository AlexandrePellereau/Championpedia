package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import com.alexpell.championpedia.DB.Comment;
import com.alexpell.championpedia.databinding.ActivityMainBinding;
import com.alexpell.championpedia.legacy.CommentActivity;
import com.alexpell.championpedia.legacy.CommentModel;
import com.alexpell.championpedia.legacy.CommentRecyclerViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class));

        /*
        if (getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE).getBoolean("loggedIn", false))
            startActivity(new Intent(getApplicationContext(), LandingPage.class));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("createAccount", false);
                startActivity(intent);
            }
        });

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("createAccount", true);
                startActivity(intent);
            }
        });
         */
    }
}