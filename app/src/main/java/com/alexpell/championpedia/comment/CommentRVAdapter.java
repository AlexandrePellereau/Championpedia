package com.alexpell.championpedia.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexpell.championpedia.R;

import java.util.ArrayList;

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.MyViewHolder>{

    Context context;
    ArrayList<CommentModel> mCommentModels;

    public CommentRVAdapter(Context context, ArrayList<CommentModel> commentModels) {
        this.context = context;
        this.mCommentModels = commentModels;
    }

    @NonNull
    @Override
    public CommentRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.comment_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentRVAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mCommentModels.get(position).getName());
        holder.date.setText(mCommentModels.get(position).getDate());
        holder.content.setText(mCommentModels.get(position).getContent());
        holder.image.setImageResource(mCommentModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mCommentModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, content;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.avatar);
        }
    }
}
