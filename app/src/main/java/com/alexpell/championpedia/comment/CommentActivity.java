package com.alexpell.championpedia.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alexpell.championpedia.R;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    ArrayList<CommentModel> mCommentModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        SetUpMyRecyclerViewModels();

        CommentRVAdapter commentRVAdapter = new CommentRVAdapter(this, mCommentModels);
        recyclerView.setAdapter(commentRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void SetUpMyRecyclerViewModels() {
        mCommentModels.add(new CommentModel("name1", "date1", "content1", R.drawable.aatrox));
        mCommentModels.add(new CommentModel("name2", "date2", "content2", R.drawable.ahri));
        mCommentModels.add(new CommentModel("name3", "date3", "content3", R.drawable.akali));
        /*
        String[] names = getResources().getStringArray(R.array.comment_tests);

        for (String name : names) {
            myRecyclerViewModels.add(new MyRecyclerViewModel(name));
        }
         */
    }
}