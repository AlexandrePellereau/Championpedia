package com.alexpell.championpedia.legacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexpell.championpedia.R;
import com.alexpell.championpedia.databinding.ActivityRecyclerViewBinding;

import java.util.ArrayList;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CommentModel> commentList;

    public CommentRecyclerViewAdapter(Context context, ArrayList<CommentModel> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityRecyclerViewBinding binding = ActivityRecyclerViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        View view = binding.getRoot();

        //LayoutInflater inflater = LayoutInflater.from(context);
        //View view = inflater.inflate(R.layout.comment_row_legacy, parent, false);
        return new CommentRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentRecyclerViewAdapter.MyViewHolder holder, int position) {
        CommentModel comment = commentList.get(position);

        //holder.name.setText(comment.getName());
        //holder.date.setText(comment.getDate());
        holder.content.setText(comment.getContent());
        //holder.image.setImageResource(comment.getImage());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //TextView name;
        //TextView date;
        TextView content;
        //ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //name = itemView.findViewById(R.id.name);
            //date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            //image = itemView.findViewById(R.id.image);
        }
    }
}
