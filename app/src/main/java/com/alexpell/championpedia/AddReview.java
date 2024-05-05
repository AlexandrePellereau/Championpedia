package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.databinding.ActivityAddReviewBinding;

public class AddReview extends AppCompatActivity {

    int difficulty = 0;
    int fun = 0;

    ActivityAddReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.removeDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficulty > 1) {
                    difficulty--;
                    binding.difficulty.setText(String.valueOf(difficulty));
                }
                else
                    Toast.makeText(AddReview.this, "Cannot go below1", Toast.LENGTH_SHORT).show();

            }
        });

        binding.addDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficulty < 5) {
                    difficulty++;
                    binding.difficulty.setText(String.valueOf(difficulty));
                }
                else
                    Toast.makeText(AddReview.this,"Cannot go further than 5",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddReview.this, "Cannot go below1", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddReview.this,"Cannot go further than 5",Toast.LENGTH_SHORT).show();
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (fun == 0 || difficulty == 0)
                    Toast.makeText(AddReview.this,"Review values cannot be 0",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(AddReview.this, "Review added succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddReview.this,ChampionActivity.class));
                }
            }
        });
    }
}