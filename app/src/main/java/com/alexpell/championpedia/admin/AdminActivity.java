package com.alexpell.championpedia.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.User;
import com.alexpell.championpedia.R;
import com.alexpell.championpedia.comment.CommentModel;
import com.alexpell.championpedia.comment.CommentRVAdapter;
import com.alexpell.championpedia.databinding.ActivityAdminBinding;
import com.alexpell.championpedia.databinding.ActivityCommentBinding;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;
    ArrayList<UserModel> userModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpUserModels();
        RecyclerView recyclerView = binding.adminRecyclerView;
        UserRVAdapter userRVAdapter = new UserRVAdapter(this, userModels);
        recyclerView.setAdapter(userRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpUserModels() {
        AllDAO allDAO = AppDataBase.getInstance(getApplicationContext()).getAllDAO();
        for (User user : allDAO.getUsers()) {
            userModels.add(new UserModel(user.getId(), user.getUsername()));
        }
    }
}