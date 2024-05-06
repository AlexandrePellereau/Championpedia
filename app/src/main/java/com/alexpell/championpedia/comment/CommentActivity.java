package com.alexpell.championpedia.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.Comment;
import com.alexpell.championpedia.MainActivity;
import com.alexpell.championpedia.R;
import com.alexpell.championpedia.databinding.ActivityCommentBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentActivity extends AppCompatActivity {

    ActivityCommentBinding binding;
    ArrayList<CommentModel> commentModels = new ArrayList<>();

    AllDAO allDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();
        String championName = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE)
                .getString("champion","velkoz");
        int championId = allDAO.getChampionByName(championName).getId();

        setUpCommentModels(championId);
        RecyclerView recyclerView = binding.commentRecyclerView;
        CommentRVAdapter commentRVAdapter = new CommentRVAdapter(this, commentModels);
        recyclerView.setAdapter(commentRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = allDAO.getUser(getUserId()).getUsername();
                String date = getDateTime();
                String content = binding.commentEditText.getText().toString();
                allDAO.insert(new Comment(championId, getUserId(), content, date));//TODO : change championId to the current champion
                Comment comment = allDAO.getLastComment();
                commentModels.add(new CommentModel(comment.getId(), username, date, content, MainActivity.getImageFromUsername(username)));
                commentRVAdapter.notifyItemInserted(commentModels.size() - 1);

                if (commentModels.size() == 1) {
                    binding.commentEmpty.setVisibility(View.GONE);
                }
            }
        });
        
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int getUserId() {
        return getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE).getInt("userId", 42);
    }

    private void setUpCommentModels(int championId) {

        List<Comment> comments = allDAO.getCommentsByChampion(championId);
        if (comments.size() == 0) {
            binding.commentEmpty.setVisibility(View.VISIBLE);
            return;
        }
        for (Comment comment : comments) {
            String username = allDAO.getUser(comment.getUserId()).getUsername();
            commentModels.add(new CommentModel(comment.getId(), username, comment.getPublicationDate(), comment.getContent(), MainActivity.getImageFromUsername(username)));
        }
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private int getImages(String username) {
        //make the sum of all letters (translated to ASCII) in the username
        //then use a modulo to get an image from the list
        List<Integer> images = Arrays.asList(R.drawable.aatrox, R.drawable.ahri, R.drawable.akali, R.drawable.karma, R.drawable.velkoz);
        int sum = 0;
        for (int i = 0; i < username.length(); i++) {
            sum += (int) username.charAt(i);
        }
        int index = sum % images.size();
        return images.get(index);
    }

    public static Intent CommentIntentFactory(Context context){
        return new Intent(context, CommentActivity.class);
    }
}