package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    ArrayList<MyRecyclerViewModel> myRecyclerViewModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        SetUpMyRecyclerViewModels();

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, myRecyclerViewModels);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void SetUpMyRecyclerViewModels() {
        myRecyclerViewModels.add(new MyRecyclerViewModel("name1", "date1", "content1", R.drawable.aatrox));
        myRecyclerViewModels.add(new MyRecyclerViewModel("name2", "date2", "content2", R.drawable.ahri));
        myRecyclerViewModels.add(new MyRecyclerViewModel("name3", "date3", "content3", R.drawable.akali));
        /*
        String[] names = getResources().getStringArray(R.array.comment_tests);

        for (String name : names) {
            myRecyclerViewModels.add(new MyRecyclerViewModel(name));
        }
         */
    }
}