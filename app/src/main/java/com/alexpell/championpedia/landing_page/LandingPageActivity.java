package com.alexpell.championpedia.landing_page;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.MainActivity;
import com.alexpell.championpedia.R;
import com.alexpell.championpedia.admin.AdminActivity;
import com.alexpell.championpedia.champion.Champion;
import com.alexpell.championpedia.databinding.ActivityLandingPageBinding;

import java.util.ArrayList;
import java.util.List;

public class LandingPageActivity extends AppCompatActivity {

    AllDAO allDAO;
    ArrayList<ChampionModel> championModels = new ArrayList<>();
    SharedPreferences sharedPreferences;
    //ActivityLandingPageBinding binding;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        //binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_landing_page);

        //check if user is admin
        Button adminButton = findViewById(R.id.admin_button);
        if (sharedPreferences.getBoolean("isAdmin",false)){
            adminButton.setVisibility(View.VISIBLE);
        }
        else{
            adminButton.setVisibility(View.INVISIBLE);
        }

        //log as user
        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();
        TextView loginText = findViewById(R.id.login_text);
        loginText.setText(String.format("%s%s", getString(R.string.logged_in_as), allDAO.getUser(sharedPreferences.getInt("userId", 0)).getUsername()));

        setUpChampionModels();
        RecyclerView recyclerView = findViewById(R.id.championRecyclerView);
        ChampionRVAdapter championRVAdapter = new ChampionRVAdapter(this, championModels);
        recyclerView.setAdapter(championRVAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        findViewById(R.id.logout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putBoolean("loggedIn",false);
                editor.putBoolean("isAdmin",false);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        findViewById(R.id.admin_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
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
}
