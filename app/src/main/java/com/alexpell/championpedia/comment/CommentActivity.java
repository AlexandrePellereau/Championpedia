package com.alexpell.championpedia.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
    ArrayList<CommentModel> mCommentModels = new ArrayList<>();

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
                .getUserDAO();


        SetUpMyRecyclerViewModels();
        RecyclerView recyclerView = binding.commentRecyclerView;
        CommentRVAdapter commentRVAdapter = new CommentRVAdapter(this, mCommentModels);
        recyclerView.setAdapter(commentRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getSharedPreferences("com.alexpell.championpedia", MODE_PRIVATE).getString("username", "username");
                String date = getDateTime();
                String content = binding.commentEditText.getText().toString();
                mCommentModels.add(new CommentModel(username, date, content, R.drawable.aatrox));
                //commentRVAdapter.notifyDataSetChanged();
                commentRVAdapter.notifyItemInserted(mCommentModels.size() - 1);
            }
        });
    }

    private void AddCommentToDB(String username, String date, String content) {
        allDAO.insert(new Comment(1, 1, content, date));
    }

    private void SetUpMyRecyclerViewModels() {
        mCommentModels.add(new CommentModel("name1", "date1", "content1", getImages("name1")));
        mCommentModels.add(new CommentModel("name2", "date2", "content2", getImages("name2")));
        mCommentModels.add(new CommentModel("name3", "date3", "content3", getImages("name3")));
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