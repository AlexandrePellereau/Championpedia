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
import com.alexpell.championpedia.MainActivity;
import com.alexpell.championpedia.comment.CommentActivity;
import com.alexpell.championpedia.databinding.ActivityChampionBinding;
import com.alexpell.championpedia.landing_page.LandingPageActivity;

import java.util.List;

public class ChampionActivity extends AppCompatActivity {

    ActivityChampionBinding binding;
    SharedPreferences sharedPreferences;
    AllDAO allDAO;
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
                startActivity(LandingPageActivity.LandingPageIntentFactory(getApplicationContext()));
            }
        });

        binding.fullLoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FullLoreActivity.FullLoreIntentFactory(getApplicationContext()));
            }
        });

        binding.addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = allDAO.getUser(sharedPreferences.getInt("userId",0)).getUsername() ;
                if (allDAO.getReview(username,championName) == null)
                    startActivity(AddReviewActivity.AddReviewIntentFactory(getApplicationContext()));
                else {
                    Toast.makeText(getApplicationContext(),"You already added a review for this character.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CommentActivity.CommentIntentFactory(getApplicationContext()));
            }
        });

        setTexts(champion);
    }

    private void setTexts(Champion champion) {
        int resourceId = getResources().getIdentifier(MainActivity.parseString(champion.getName()), "drawable", getPackageName());

        binding.championName.setText(champion.getName());

        binding.championImage.setImageResource(resourceId);

        binding.championLore.setText(champion.getLore());

        binding.banrate.setText(champion.getBanrate() + " %");

        binding.pickrate.setText(champion.getPickrate() + " %");

        binding.winrate.setText(champion.getWinrate() + " %");



        List<Integer> difficulties = allDAO.getChampionsReviewDifficulty(champion.getName());

        int sum = 0;

        for (Integer i : difficulties){
            sum += i;
        }

        if (sum != 0)
            binding.difficulty.setText(String.valueOf(sum/difficulties.size()));
        else {
            binding.difficulty.setText("No reviews");
        }

        List<Integer> fun = allDAO.getChampionsReviewFun(champion.getName());

        sum = 0;

        for (Integer i : fun){
            sum += i;
        }

        if (sum != 0)
            binding.fun.setText(String.valueOf(sum/fun.size()));
        else {
            binding.fun.setText("No reviews");
        }
        
    }

    public static Intent ChampionActivityIntentFactory(Context context){
        return new Intent(context,ChampionActivity.class);
    }
}