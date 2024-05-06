package com.alexpell.championpedia.landing_page;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.R;
import com.alexpell.championpedia.champion.ChampionActivity;

import java.util.ArrayList;

public class ChampionRVAdapter extends RecyclerView.Adapter<ChampionRVAdapter.MyViewHolder>{

    Context context;
    ArrayList<ChampionModel> championModels;

    public ChampionRVAdapter(Context context, ArrayList<ChampionModel> championModels) {
        this.context = context;
        this.championModels = championModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.champion_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChampionModel championModel = championModels.get(position);
        holder.name.setText(championModel.getName());
        holder.image.setImageResource(championModel.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("champion", championModel.getName());
                editor.apply();
                context.startActivity(new Intent(context, ChampionActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return championModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}
