package com.alexpell.championpedia.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.alexpell.championpedia.R;
import com.alexpell.championpedia.databinding.ActivityCommentBinding;
import com.alexpell.championpedia.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CommentActivity extends AppCompatActivity {

    ActivityCommentBinding binding;
    ArrayList<CommentModel> mCommentModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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

    private void SetUpMyRecyclerViewModels() {
        mCommentModels.add(new CommentModel("name1", "date1", "content1", R.drawable.aatrox));
        mCommentModels.add(new CommentModel("name2", "date2", "content2", R.drawable.ahri));
        mCommentModels.add(new CommentModel("name3", "date3", "content3", R.drawable.akali));
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}