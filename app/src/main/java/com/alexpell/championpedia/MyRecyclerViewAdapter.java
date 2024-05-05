package com.alexpell.championpedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<MyRecyclerViewModel> myRecyclerViewModels;

    public MyRecyclerViewAdapter(Context context, ArrayList<MyRecyclerViewModel> myRecyclerViewModels) {
        this.context = context;
        this.myRecyclerViewModels = myRecyclerViewModels;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.name.setText(myRecyclerViewModels.get(position).getName());
        holder.date.setText(myRecyclerViewModels.get(position).getDate());
        holder.content.setText(myRecyclerViewModels.get(position).getContent());
        holder.image.setImageResource(myRecyclerViewModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return myRecyclerViewModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, content;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);
        }
    }
}
