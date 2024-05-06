package com.alexpell.championpedia.landing_page;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.MainActivity;
import com.alexpell.championpedia.R;
import com.alexpell.championpedia.admin.AdminActivity;
import com.alexpell.championpedia.champion.Champion;
import com.alexpell.championpedia.champion.ChampionActivity;
import com.alexpell.championpedia.comment.CommentRVAdapter;
import com.alexpell.championpedia.databinding.ActivityLandingPageBinding;

import java.util.ArrayList;
import java.util.List;

public class LandingPageActivity extends AppCompatActivity {

    AllDAO allDAO;
    ArrayList<ChampionModel> championModels = new ArrayList<>();
    SharedPreferences sharedPreferences;
    ActivityLandingPageBinding binding;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_landing_page2);

        //check if user is admin
        if (sharedPreferences.getBoolean("isAdmin",false)){
            binding.adminButton.setVisibility(View.VISIBLE);
        }
        else{
            binding.adminButton.setVisibility(View.INVISIBLE);
        }

        //log as user
        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();
        binding.loginText.setText(String.format("%s%s", getString(R.string.logged_in_as), allDAO.getUser(sharedPreferences.getInt("userId", 0)).getUsername()));

        setUpChampionModels();
        RecyclerView recyclerView = findViewById(R.id.championRecyclerView);
        ChampionRVAdapter championRVAdapter = new ChampionRVAdapter(this, championModels);
        recyclerView.setAdapter(championRVAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putBoolean("loggedIn",false);
                editor.putBoolean("isAdmin",false);
                editor.apply();
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext()));
            }
        });
        binding.adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AdminActivity.AdminIntentFactory(getApplicationContext()));
            }
        });
    }

    public static String ParseString(String s) {
        return s.toLowerCase().replace("'","").replace(" ","").replace(".","");
    }

    private void setUpChampionModels() {
        List<Champion> champions = allDAO.getAllChampions();
        for (Champion champion : champions) {
            int image = getResources().getIdentifier(ParseString(champion.getName()), "drawable", getPackageName());
            championModels.add(new ChampionModel(champion.getName(), image));
        }

    }

    public static Intent LandingPageIntentFactory(Context context){
        return new Intent(context, LandingPageActivity.class);
    }
}
