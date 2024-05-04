package com.alexpell.championpedia.legacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.R;
import com.alexpell.championpedia.databinding.ActivityCommentBinding;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    ActivityCommentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        List<CommentModel> commentList = new ArrayList<>();
        commentList.add(new CommentModel("Alex Pell", "2021-07-01", "This is a comment", R.drawable.aatrox));
        commentList.add(new CommentModel("Predatoria <3 Dralexgon", "2289-07-01", "This is a weird comment", R.drawable.ahri));

        RecyclerView recyclerView = findViewById(R.id.commentRecyclerView);
        CommentRecyclerViewAdapter adapter = new CommentRecyclerViewAdapter(this, commentList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toast.makeText(this, "This is a test", Toast.LENGTH_SHORT).show();
    }
}