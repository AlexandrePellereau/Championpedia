package com.alexpell.championpedia.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.Comment;
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

        allDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getAllDAO();


        setUpCommentModels();
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
                commentModels.add(new CommentModel(username, date, content, getImages(username)));
                //commentRVAdapter.notifyDataSetChanged();
                commentRVAdapter.notifyItemInserted(commentModels.size() - 1);
                AddCommentToDB(getUserId(), date, content);
            }
        });
    }

    private int getUserId() {
        return getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE).getInt("userId", 42);
    }

    private void AddCommentToDB(int userId, String date, String content) {
        allDAO.insert(new Comment(1, userId, content, date));
    }

    private void setUpCommentModels() {
        //TODO : remove example comments and add a textView "No comments yet" if there are no comments
        commentModels.add(new CommentModel("name1", "date1", "content1", getImages("name1")));
        commentModels.add(new CommentModel("name2", "date2", "content2", getImages("name2")));
        for (Comment comment : allDAO.getComments(1)) {
            String username = allDAO.getUser(comment.getUserId()).getUsername();
            commentModels.add(new CommentModel(username, comment.getPublicationDate(), comment.getContent(), getImages(username)));
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
}