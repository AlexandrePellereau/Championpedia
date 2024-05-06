package com.alexpell.championpedia.champion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.Review;
import com.alexpell.championpedia.MainActivity;
import com.alexpell.championpedia.databinding.ActivityAddReviewBinding;

public class AddReviewActivity extends AppCompatActivity {

    int difficulty = 0;
    int fun = 0;

    SharedPreferences sharedPreferences;

    ActivityAddReviewBinding binding;
    AllDAO allDAO;
    Champion champion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
        String championName = sharedPreferences.getString("champion","velkoz");
        champion = allDAO.getChampionByName(championName);


        binding.removeDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficulty > 1) {
                    difficulty--;
                    binding.difficulty.setText(String.valueOf(difficulty));
                }
                else
                    Toast.makeText(AddReviewActivity.this, "Cannot go below1", Toast.LENGTH_SHORT).show();

            }
        });

        int resourceId = getResources().getIdentifier(MainActivity.parseString(champion.getName()), "drawable", getPackageName());

        binding.championImage.setImageResource(resourceId);
        binding.championName.setText(championName);


        binding.addDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficulty < 5) {
                    difficulty++;
                    binding.difficulty.setText(String.valueOf(difficulty));
                }
                else
                    Toast.makeText(AddReviewActivity.this,"Cannot go further than 5",Toast.LENGTH_SHORT).show();
            }
        });

        binding.removeFunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fun > 1) {
                    fun--;
                    binding.fun.setText(String.valueOf(fun));
                }
                else
                    Toast.makeText(AddReviewActivity.this, "Cannot go below1", Toast.LENGTH_SHORT).show();
            }
        });

        binding.addFunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fun < 5) {
                    fun++;
                    binding.fun.setText(String.valueOf(fun));
                }
                else
                    Toast.makeText(AddReviewActivity.this,"Cannot go further than 5",Toast.LENGTH_SHORT).show();
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (fun == 0 || difficulty == 0)
                    Toast.makeText(AddReviewActivity.this,"Review values cannot be 0",Toast.LENGTH_SHORT).show();
                else {

                    Toast.makeText(AddReviewActivity.this, "Review added succesfully", Toast.LENGTH_SHORT).show();
                    String username = allDAO.getUser(sharedPreferences.getInt("userId",0)).getUsername() ;
                    allDAO.insert(new Review(username,champion.getName(),difficulty,fun));
                    startActivity(new Intent(AddReviewActivity.this,ChampionActivity.class));
                }
            }
        });
    }

    public static Intent AddReviewIntentFactory(Context context) {
        return new Intent(context, AddReviewActivity.class);
    }
}