package com.alexpell.championpedia.legacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.MyRecyclerViewModel;
import com.alexpell.championpedia.R;
import com.alexpell.championpedia.databinding.ActivityCommentBinding;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    ActivityCommentBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Toast.makeText(this, "This is test 1", Toast.LENGTH_SHORT).show();

        ArrayList<CommentModel> comments = new ArrayList<>();
        comments.add(new CommentModel("Alex",  "22/04","This is a comment", 1));
        comments.add(new CommentModel("tets2",  "22/04","This is a commeeeeent", 1));

        RecyclerView recyclerView = findViewById(R.id.commentRecyclerView);
        CommentRecyclerViewAdapter commentAdapter = new CommentRecyclerViewAdapter(this, comments);
        recyclerView.setAdapter(commentAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Toast.makeText(this, "This is test 2", Toast.LENGTH_SHORT).show();
    }


}